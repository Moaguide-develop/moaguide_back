package com.moaguide.service;

import com.moaguide.domain.user.User;
import com.moaguide.domain.user.UserRepository;
import com.moaguide.dto.NewDto.oauth.CustomOAuth2User;
import com.moaguide.dto.NewDto.oauth.GoogleResponse;
import com.moaguide.dto.NewDto.oauth.NaverResponse;
import com.moaguide.dto.NewDto.oauth.OAuth2ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2ResponseDto oAuth2Response = null;

        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String email = oAuth2Response.getEmail();
        User existData = userRepository.findByEmailAndLoginType(email, registrationId).orElse(null);

        if (existData == null) {
            return new CustomOAuth2User(new User(oAuth2Response.getEmail(), registrationId));
        } else {
            return new CustomOAuth2User(existData);
        }
    }
}