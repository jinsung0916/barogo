package com.barogo.delivery.app.delivery.service;

import com.barogo.delivery.app.delivery.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = DeliveryServiceImpl.class)
class DeliveryServiceTest {

    @Autowired
    private DeliveryService deliveryService;

    @MockBean
    private DeliveryManagerRepository deliveryManagerRepository;
    @MockBean
    private DeliveryRepository deliveryRepository;

    @BeforeEach
    void beforeEach() {
        given(deliveryManagerRepository.findOne())
                .willReturn(Optional.of(() -> (delivery, newAddress) -> true));

        given(deliveryRepository.findById(any()))
                .willReturn(
                        Optional.of(
                                Delivery.builder()
                                        .registeredTime(LocalDateTime.now())
                                        .address(
                                                DeliveryAddress.builder()
                                                        .postCode("")
                                                        .address("")
                                                        .addressDetail("")
                                                        .geoLocation(
                                                                GeoLocation.builder()
                                                                        .latitude(0)
                                                                        .longitude(0)
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .memberId(0L)
                                        .build()
                        )
                );
        given(deliveryRepository.findByMemberIdAndRegisteredTimeBetween(any(), any(), any(), any()))
                .willReturn(Page.empty());
    }

    @Test
    @DisplayName("배달 리스트를 조회한다.")
    void getList() {
        // Given
        Long memberId = 0L;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        Pageable pageable = Pageable.unpaged();

        // When
        Page<Delivery> deliveries = deliveryService.getList(memberId, start, end, pageable);

        // Then
        Assertions.assertThat(deliveries.getSize()).isEqualTo(0);
    }

    @Test
    @DisplayName("배달 주소를 수정한다.")
    void updateAddress() {
        // Given
        Long deliveryId = 0L;
        DeliveryAddress newAddress = DeliveryAddress.builder()
                .postCode("06060")
                .address("서울 강남구 언주로134길 32")
                .addressDetail("바로고 본사")
                .geoLocation(
                        GeoLocation.builder()
                                .latitude(0)
                                .longitude(0)
                                .build()
                )
                .build();

        // When
        Delivery delivery = deliveryService.updateAddress(deliveryId, newAddress);

        // Then
        Assertions.assertThat(delivery.getAddress()).usingRecursiveComparison().isEqualTo(newAddress);
    }

}