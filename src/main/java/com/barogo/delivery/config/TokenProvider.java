package com.barogo.delivery.config;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    Authentication toAuthentication(String token);

    String toToken(Authentication authentication);

}
