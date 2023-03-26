package com.barogo.delivery.app.member.controller;

import com.barogo.delivery.app.member.domain.Member;
import com.barogo.delivery.app.member.domain.MemberRepository;
import com.barogo.delivery.app.member.domain.Name;
import com.barogo.delivery.app.member.domain.Password;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(
            @Autowired MemberRepository memberRepository,
            @Autowired PasswordEncoder passwordEncoder
    ) {
        Member member = Member.builder()
                .loginId("username")
                .password(
                        Password.builder()
                                .password("ABCabc123!@#")
                                .encoder(passwordEncoder)
                                .build()
                )
                .name(
                        Name.builder()
                                .firstName("firstName")
                                .lastName("lastName")
                                .build()
                )
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("로그인 요청을 처리한다.")
    void authenticate() throws Exception {
        // Given
        LoginCommand command = LoginCommand.builder()
                .username("username")
                .password("ABCabc123!@#")
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(
                        post("/authenticate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(command))
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }

}