package com.barogo.delivery.app.member.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordTest {

    private final PasswordEncoder encoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return false;
        }
    };

    @Test
    @DisplayName("비밀번호를 생성한다.")
    void create() {
        // Given
        String passwordString = "ABCabc123!@#";

        // When
        Password password = Password.builder()
                .password(passwordString)
                .encoder(encoder)
                .build();

        // Then
        Assertions.assertThat(password.getPassword()).isEqualTo(passwordString);
    }

    @Test
    @DisplayName("길이가 12 미만인 경우 예외 처리한다.")
    void createEx_1() {
        // Given
        String passwordString = "ABCabc123";

        // When
        ThrowableAssert.ThrowingCallable callable = () -> {
            Password.builder()
                    .password(passwordString)
                    .encoder(encoder)
                    .build();
        };

        // Then
        Assertions.assertThatThrownBy(callable).isInstanceOf(Password.IllegalPasswordException.class);
    }

    @Test
    @DisplayName("대문자/소문자/숫자/특수문자 중 3 가지 이상을 포함하지 않을 경우 예외 처리한다.")
    void createEx_2() {
        // Given
        String passwordString = "ABCabcABCabc";

        // When
        ThrowableAssert.ThrowingCallable callable = () -> {
            Password.builder()
                    .password(passwordString)
                    .encoder(encoder)
                    .build();
        };

        // Then
        Assertions.assertThatThrownBy(callable).isInstanceOf(Password.IllegalPasswordException.class);
    }

}