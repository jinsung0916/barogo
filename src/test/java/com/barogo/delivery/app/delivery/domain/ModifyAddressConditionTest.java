package com.barogo.delivery.app.delivery.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ModifyAddressConditionTest {

    private static final ModifyAddressCondition statusCond = new DeliveryStatusCondition(DeliveryStatus.REGISTERED);
    private static final ModifyAddressCondition distanceCond = new DistanceCondition(5);
    private static final ModifyAddressCondition andCondition = new AndModifyCondition(statusCond, distanceCond);

    @Test
    @DisplayName("배달 상태 조건을 만족할 경우 주소를 변경한다.")
    void statusCondTest() {
        // Given
        Delivery delivery = getMockDelivery();

        ModifyAddressCondition statusCond = new DeliveryStatusCondition(DeliveryStatus.REGISTERED);
        DeliveryAddress newAddress = getMockDeliveryAddress();

        // When
        delivery.changeAddress(statusCond, newAddress);

        // Then
        Assertions.assertThat(delivery.getAddress()).usingRecursiveComparison().isEqualTo(newAddress);
    }

    @Test
    @DisplayName("배달 상태 조건을 만족하지 않을 경우 예외가 발생한다.")
    void statusCondTestEx_1() {
        // Given
        Delivery delivery = getMockDelivery();
        delivery.assign(LocalDateTime.now(), 0L);

        ModifyAddressCondition statusCond = new DeliveryStatusCondition(DeliveryStatus.REGISTERED);
        DeliveryAddress newAddress = getMockDeliveryAddress();

        // When
        Throwable throwable = Assertions.catchThrowable(
                () -> delivery.changeAddress(statusCond, newAddress)
        );

        // Then
        Assertions.assertThat(throwable).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("거리 조건을 만족할 경우 주소를 변경한다.")
    void distanceCondTest() {
        // Given
        Delivery delivery = getMockDelivery();

        ModifyAddressCondition statusCond = new DistanceCondition(10000);
        DeliveryAddress newAddress = getMockDeliveryAddress();

        // When & Then
        delivery.changeAddress(statusCond, newAddress);

        // Then
        Assertions.assertThat(delivery.getAddress()).usingRecursiveComparison().isEqualTo(newAddress);
    }

    @Test
    @DisplayName("거리 조건을 만족하지 않을 경우 예외가 발생한다.")
    void distanceCondTestEx_1() {
        // Given
        Delivery delivery = getMockDelivery();

        ModifyAddressCondition statusCond = new DistanceCondition(5000);
        DeliveryAddress newAddress = getMockDeliveryAddress();

        // When
        Throwable throwable = Assertions.catchThrowable(
                () -> delivery.changeAddress(statusCond, newAddress)
        );

        // Then
        Assertions.assertThat(throwable).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("변경 이력 조건을 만족할 경우 주소를 변경한다.")
    void maxCountCondText() {
        // Given
        Delivery delivery = getMockDelivery();

        ModifyAddressCondition statusCond = new MaxCountCondition(2);
        DeliveryAddress newAddress = getMockDeliveryAddress();

        // When & Then
        delivery.changeAddress(statusCond, newAddress);

        // Then
        Assertions.assertThat(delivery.getAddress()).usingRecursiveComparison().isEqualTo(newAddress);
    }

    @Test
    @DisplayName("변경 이력 조건을 만족하지 않을 경우 예외가 발생한다.")
    void maxCountCondTestEx_1() {
        // Given
        Delivery delivery = getMockDelivery();

        ModifyAddressCondition statusCond = new MaxCountCondition(2);
        DeliveryAddress newAddress = getMockDeliveryAddress();


        // When
        delivery.changeAddress(statusCond, newAddress);

        Throwable throwable = Assertions.catchThrowable(
                () -> delivery.changeAddress(statusCond, newAddress)
        );

        // Then
        Assertions.assertThat(throwable).isInstanceOf(IllegalStateException.class);
    }

    private Delivery getMockDelivery() {
        LocalDateTime registeredTime = LocalDateTime.now();
        DeliveryAddress address = DeliveryAddress.builder()
                .postCode("06232")
                .address("서울 강남구 강남대로 396")
                .addressDetail("강남역 2호선")
                .geoLocation(
                        GeoLocation.builder()
                                .latitude(37.49753592034759)
                                .longitude(127.0275816520584)
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

    private DeliveryAddress getMockDeliveryAddress() {
        return DeliveryAddress.builder()
                .postCode("06060")
                .address("서울 강남구 언주로134길 32")
                .addressDetail("바로고 본사")
                .geoLocation(
                        GeoLocation.builder()
                                .latitude(37.51690227196791)
                                .longitude(127.03834319810912)
                                .build()
                )
                .build();
    }

}
