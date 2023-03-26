package com.barogo.delivery.app.member.domain;

import com.barogo.delivery.util.AssertUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    private String firstName;
    private String lastName;

    @Builder
    public Name(String firstName, String lastName) {
        AssertUtils.AssertNull(firstName, lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }

}
