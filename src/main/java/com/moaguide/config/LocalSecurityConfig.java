package com.moaguide.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moaguide.config.handler.CustomAccessDeniedHandler;
import com.moaguide.config.handler.CustomLogoutSuccessHandler;
import com.moaguide.config.handler.OAuth2SuccessHandler;
import com.moaguide.jwt.JWTFilter;
import com.moaguide.jwt.JWTUtil;
import com.moaguide.security.LocalLoginFilter;
import com.moaguide.service.CookieService;
import com.moaguide.service.CustomOAuth2UserService;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Profile("local")
public class LocalSecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final CookieService cookieService;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                    cors.configurationSource(corsConfigurationzSource());
                });
        //csrf disable
        http
                .csrf((auth) -> auth.disable());
        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());
        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());
        http.
                addFilterBefore(new CorsFilter(corsConfigurationzSource()), ChannelProcessingFilter.class);
        http

                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/logout", "/user/update/nickname").authenticated() // 특정 경로에 대해서만 인증 필요
                        .anyRequest().permitAll()
                );
        // 특정 경로에 대해서만 JWTFilter 적용
        http.
                addFilterAt(new LocalLoginFilter(authenticationManager(authenticationConfiguration),jwtUtil,cookieService), UsernamePasswordAuthenticationFilter.class);
        http
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService) // 사용자 정보 가져오기
                        )
                        .successHandler(new OAuth2SuccessHandler(jwtUtil,cookieService)) // OAuth2 성공 핸들러 직접 호출
                );
        http.
                sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 요청 URL
                        .deleteCookies("JSESSIONID", "refresh", "rememberMe")
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler()) // 로그아웃 성공 후 메시지 반환
                );
        return http.build();
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return  new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // JSON 변환을 위한 ObjectMapper
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Java 8 날짜/시간 모듈 등록
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationzSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("https://moaguide.com", "https://moaguide.vercel.app/","https://www.moaguide.com","https://moaguide-admin.vercel.app"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // 허용 메서드
        configuration.setAllowedHeaders(Arrays.asList("Authorization","verify", "Content-Type","cookie")); // 허용 헤더
        configuration.setExposedHeaders(Arrays.asList("Authorization","verify")); // 클라이언트가 접근할 수 있는 응답 헤더
        configuration.setAllowCredentials(true); // 자격증명 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}