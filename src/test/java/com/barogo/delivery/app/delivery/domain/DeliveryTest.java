package com.barogo.delivery.app.delivery.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DeliveryTest {

    @Test
    @DisplayName("배달을 생성한다.")
    void create() {
        // Given
        LocalDateTime registeredTime = LocalDateTime.now();
        DeliveryAddress address = DeliveryAddress.builder()
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
        Long memberId = 1L;

        // When
        Delivery delivery = Delivery.builder()
                .registeredTime(registeredTime)
                .address(address)
                .memberId(memberId)
                .build();

        // then
        Assertions.assertThat(delivery.getDeliveryStatus()).isEqualTo(DeliveryStatus.REGISTERED);
        Assertions.assertThat(delivery.getRegisteredTime()).isEqualTo(registeredTime);
        Assertions.assertThat(delivery.getAddress()).usingRecursiveComparison().isEqualTo(address);
        Assertions.assertThat(delivery.getMemberId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("배차한다.")
    void assign() {
        // Given
        Delivery delivery = getMockInstance();
        LocalDateTime assignTime = LocalDateTime.now();
        Long riderId = 1L;

        // When
        delivery.assign(assignTime, riderId);

        // Then
        Assertions.assertThat(delivery.getDeliveryStatus()).isEqualTo(DeliveryStatus.ASSIGNED);
        Assertions.assertThat(delivery.getAssignedTime()).isEqualTo(assignTime);
        Assertions.assertThat(delivery.getRiderId()).isEqualTo(riderId);
    }

    @Test
    @DisplayName("배차를 취소한다.")
    void cancelAssign() {
        // Given
        Delivery delivery = getMockInstance();
        LocalDateTime cancelTime = LocalDateTime.now();

        // When
        delivery.cancelAssign(cancelTime);

        // Then
        Assertions.assertThat(delivery.getDeliveryStatus()).isEqualTo(DeliveryStatus.REGISTERED);
        Assertions.assertThat(delivery.getAssignedTime()).isEqualTo(cancelTime);
        Assertions.assertThat(delivery.getRiderId()).isNull();
    }

    @Test
    @DisplayName("배달을 시작한다.")
    void start() {
        // Given
        Delivery delivery = getMockInstance();
        LocalDateTime startTime = LocalDateTime.now();

        // When
        delivery.start(startTime);

        // Then
        Assertions.assertThat(delivery.getDeliveryStatus()).isEqualTo(DeliveryStatus.DELIVERY_START);
        Assertions.assertThat(delivery.getDeliveryStartTime()).isEqualTo(startTime);
    }

    @Test
    @DisplayName("배달을 종료한다.")
    void end() {
        // Given
        Delivery delivery = getMockInstance();
        LocalDateTime endTime = LocalDateTime.now();

        // When
        delivery.end(endTime);

        // Then
        Assertions.assertThat(delivery.getDeliveryStatus()).isEqualTo(DeliveryStatus.DELIVERY_END);
        Assertions.assertThat(delivery.getDeliveryEndTime()).isEqualTo(endTime);
    }

    @Test
    @DisplayName("배달 주소를 변경한다.")
    void changeAddress() {
        // Given
        Delivery mockDelivery = getMockInstance();
        ModifyAddressCondition condition = (delivery, newAddress) -> true;
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
        mockDelivery.changeAddress(condition, newAddress);

        // Then
        Assertions.assertThat(mockDelivery.getAddress()).usingRecursiveComparison().isEqualTo(newAddress);
    }

    private Delivery getMockInstance() {
        LocalDateTime registeredTime = LocalDateTime.now();
        DeliveryAddress address = DeliveryAddress.builder()
                .postCode("")
                .address("")
                .addressDetail("")
                .geoLocation(
                        GeoLocation.builder()
                                .latitude(0)
                                .longitude(0)
                                .build()
                )
                .build();
        Long memberId = 1L;

        return Delivery.builder()
                .registeredTime(registeredTime)
                .address(address)
                .memberId(memberId)
                .build();
    }

}