package com.barogo.delivery.app.member.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class LoginCommand {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public UsernamePasswordAuthenticationToken toToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
