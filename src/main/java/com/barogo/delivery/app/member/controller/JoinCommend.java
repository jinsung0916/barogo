package com.barogo.delivery.app.member.controller;

import com.barogo.delivery.app.member.domain.Member;
import com.barogo.delivery.app.member.domain.Name;
import com.barogo.delivery.app.member.domain.Password;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class JoinCommend {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public Member toEntity(PasswordEncoder encoder) {
        return Member.builder()
                .loginId(loginId)
                .password(
                        Password.builder()
                                .password(password)
                                .encoder(encoder)
                                .build()
                )
                .name(
                        Name.builder()
                                .firstName(firstName)
                                .lastName(lastName)
                                .build()
                )
                .build();
    }

}
