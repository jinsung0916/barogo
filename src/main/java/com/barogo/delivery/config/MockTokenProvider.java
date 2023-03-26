package com.barogo.delivery.config;

import com.barogo.delivery.app.member.domain.MockMember;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MockTokenProvider implements TokenProvider {

    public Authentication toAuthentication(String token) {
        UserDetails userDetails = new MockMember();
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String toToken(Authentication authentication) {
        return "hello world!";
    }

}
