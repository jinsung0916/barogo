package com.barogo.delivery.app.member.service;

import com.barogo.delivery.app.member.domain.Member;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface MemberService {

    Member create(@NotNull Member member);

}
