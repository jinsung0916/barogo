package com.barogo.delivery.app.member.controller;

import com.barogo.delivery.config.MockTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final MockTokenProvider tokenProvider;

    @PostMapping("/authenticate")
    public LoginResponse authenticate(
            @RequestBody @Valid LoginCommand command
    ) {
        Authentication authentication = authenticationManager.authenticate(command.toToken());

        if(!authentication.isAuthenticated()) {
            throw new IllegalArgumentException();
        }

        return LoginResponse.builder()
                .accessToken(tokenProvider.toToken(authentication))
                .build();
    }

}
