package com.barogo.delivery.app.member.controller;

import com.barogo.delivery.app.member.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponse {

    private String loginId;
    private String firstName;
    private String lastName;

    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
                .loginId(member.getLoginId())
                .firstName(member.getName().getFirstName())
                .lastName(member.getName().getLastName())
                .build();
    }

}
