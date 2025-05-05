package com.moaguide.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@Profile({"blue","green"})
public class CustomClientRegistrationRepo {

    private final SocialClientRegistration socialClientRegistration;

    public CustomClientRegistrationRepo(SocialClientRegistration socialClientRegistration) {

        this.socialClientRegistration = socialClientRegistration;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(socialClientRegistration.googleClientRegistration());
    }
}
