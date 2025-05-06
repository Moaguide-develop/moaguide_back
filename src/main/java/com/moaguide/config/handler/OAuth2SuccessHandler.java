package com.moaguide.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moaguide.domain.user.User;
import com.moaguide.dto.NewDto.oauth.CustomOAuth2User;
import com.moaguide.jwt.JWTUtil;
import com.moaguide.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final CookieService cookieService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Authentication 객체에서 CustomOAuth2User 정보 가져오기
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        User user = customOAuth2User.getUser();  // User 정보 가져오기



        // ObjectMapper와 PrintWriter를 한번만 생성
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();

        try {
            if (user.getNickname() == null || user.getNickname().isEmpty()) {
                // 이름이 없는 경우, 검증용 토큰 발급 및 JSON 응답 반환
                String token = jwtUtil.createJwt("verify", user.getEmail(), "pass", 1000 * 60 * 30L);
                String email = user.getEmail();
                String login = user.getLoginType();
                MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
                queryParams.add("verify",token);
                queryParams.add("email",email);
                queryParams.add("loginType",login);

                String uri = UriComponentsBuilder
                        .newInstance()
                        .scheme("https")
                        .host("moaguide.com")
                        .path("/signup")
                        .queryParams(queryParams)
                        .build()
                        .toString();
                response.sendRedirect(uri);

            } else {
                // 이름이 있는 경우, AccessToken 및 RefreshToken 발급
                String accessToken = jwtUtil.createJwt("access", user.getNickname(), String.valueOf(user.getRole()), 60 * 1000L);
                String refreshToken = jwtUtil.createJwt("refresh", user.getNickname(), String.valueOf(user.getRole()), 24 * 60 * 60 * 1000L);

                // 응답 설정: AccessToken은 헤더, RefreshToken은 쿠키로 설정
                response.setHeader("Authorization", "Bearer " + accessToken);
                cookieService.setCookieWithSameSite(response, "refresh", refreshToken, 24 * 60 * 60 * 1000L);
                cookieService.setCookieWithSameSite(response, "rememberMe", "false", 24 * 60 * 60 * 1000L);

                response.setStatus(HttpStatus.OK.value());
                String userJson = objectMapper.writeValueAsString(user);

                // JSON 직렬화
                String userjson = objectMapper.writeValueAsString(
                        Map.of(
                                "message", "Login successful",
                                "user", userJson,
                                "loginType",user.getLoginType(),
                                "marketing",user.getMarketingConsent()
                        )
                );

                // URL에 JSON을 쿼리 파라미터로 추가
                String encodedJson = URLEncoder.encode(userjson, StandardCharsets.UTF_8);

                String uri = UriComponentsBuilder
                        .newInstance()
                        .scheme("https")
                        .host("moaguide.com")
                        .path("/")
                        .queryParam("user", encodedJson) // JSON을 하나의 파라미터로 추가
                        .queryParam("access", accessToken)
                        .build()
                        .toString();
                response.sendRedirect(uri);
            }
        } catch (IOException e) {
            // 에러 처리: 500 응답 전송
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            writer.write("{\"message\": \"An error occurred during authentication\"}");
            writer.flush();
        }

    }
}