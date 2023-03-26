package com.barogo.delivery.app.delivery.controller;

import com.barogo.delivery.app.delivery.domain.Delivery;
import com.barogo.delivery.app.delivery.domain.DeliveryAddress;
import com.barogo.delivery.app.delivery.domain.GeoLocation;
import com.barogo.delivery.app.delivery.service.DeliveryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeliveryService deliveryService;

    @BeforeEach
    void beforeEach() {
        given(deliveryService.getList(any(), any(), any()))
                .willReturn(Page.empty());

        given(deliveryService.updateAddress(any(), any()))
                .willReturn(Delivery.builder()
                        .registeredTime(LocalDateTime.now())
                        .address(
                                DeliveryAddress.builder()
                                        .postCode("06060")
                                        .address("서울 강남구 언주로134길 32")
                                        .addressDetail("바로고 본사")
                                        .geoLocation(
                                                GeoLocation.builder()
                                                        .latitude(0)
                                                        .longitude(0)
                                                        .build()
                                        )
                                        .build()
                        )
                        .memberId(0L)
                        .build());
    }

    @Test
    @DisplayName("배달 리스트를 조회한다.")
    void getListTest() throws Exception {
        // Given
        GetListCommand command = GetListCommand.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now())
                .build();

        Map<String, String> parameters = objectMapper.convertValue(command, new TypeReference<>() {
        });
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.setAll(parameters);

        // When
        ResultActions resultActions = mockMvc.perform(
                        get("/delivery")
                                .params(map)
                                .param("page", "1")
                                .param("size", "10")
                                .param("sort", "deliveryId,DESC")
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("배달 주소를 변경한다.")
    void updateAddressTest() throws Exception {
        // Given
        UpdateAddressCommand command = UpdateAddressCommand.builder()
                .postCode("12345")
                .address("주소")
                .addressDetails("상세주소")
                .latitude(0D)
                .longitude(0D)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(
                        put("/delivery/1/address")
                                .content(objectMapper.writeValueAsString(command))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is2xxSuccessful());
    }


}
