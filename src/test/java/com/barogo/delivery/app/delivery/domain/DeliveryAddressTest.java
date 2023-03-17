package com.barogo.delivery.app.delivery.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryAddressTest {

    @Test
    @DisplayName("배달 주소 간 거리를 측정한다.")
    void getDistanceFrom() {
        // Given
        DeliveryAddress address1 = DeliveryAddress.builder()
                .postCode("")
                .address("")
                .addressDetail("사당역")
                .geoLocation(
                        GeoLocation.builder()
                                .latitude(37.4764180746797)
                                .longitude(126.98158349089296)
                                .build()
                )
                .build();

        DeliveryAddress address2 = DeliveryAddress.builder()
                .postCode("")
                .address("")
                .addressDetail("강남역")
                .geoLocation(
                        GeoLocation.builder()
                                .latitude(37.49787826569854)
                                .longitude(127.02774009895653)
                                .build()
                )
                .build();

        // When
        double distance = address1.getDistanceFrom(address2);

        // Then
        Assertions.assertThat(distance).isGreaterThan(6000);
        Assertions.assertThat(distance).isLessThan(7000);
    }

}