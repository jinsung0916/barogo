package com.barogo.delivery.app.member.controller;

import com.barogo.delivery.app.member.domain.Member;
import com.barogo.delivery.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder encoder;

    private final MemberService memberService;

    @PostMapping
    public MemberResponse create(
            @RequestBody @Valid JoinCommend commend
    ) {
        Member entity = memberService.create(commend.toEntity(encoder));
        return MemberResponse.fromEntity(entity);
    }

}
