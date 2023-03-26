package com.barogo.delivery.app.member.domain;

import com.barogo.delivery.util.AssertUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    private static final String REGEX_CAPITAL = ".*[A-Z]+.*";
    private static final String REGEX_SMALL = ".*[a-z]+.*";
    private static final String REGEX_DIGIT = ".*[0-9]+.*";
    private static final String REGEX_SPECIAL = ".*[!@#$%^&*()_+-=]+.*";

    private String password;

    @Builder
    public Password(String password, PasswordEncoder encoder) {
        AssertUtils.AssertNull(password, encoder);

        if (!isValid(password)) {
            throw new IllegalPasswordException();
        }

        this.password = encoder.encode(password);
    }

    private boolean isValid(String password) {
        return isValidLength(password) && isValidCombination(password);
    }

    private boolean isValidLength(String password) {
        return password.length() >= 12;
    }

    private boolean isValidCombination(String password) {
        int count = 0;

        count += password.matches(REGEX_CAPITAL) ? 1 : 0;
        count += password.matches(REGEX_SMALL) ? 1 : 0;
        count += password.matches(REGEX_DIGIT) ? 1 : 0;
        count += password.matches(REGEX_SPECIAL) ? 1 : 0;

        return count >= 3;
    }

    @Override
    public String toString() {
        return this.password;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class IllegalPasswordException extends IllegalArgumentException {
    }

}
