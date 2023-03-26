package com.barogo.delivery.app.delivery.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @BeforeAll
    static void beforeAll(
            @Autowired DeliveryRepository deliveryRepository
    ) {
        for(int i = 1; i <= 5; i++) {
            deliveryRepository.save(getMockInstance(i));
        }
    }

    @Test
    void findByRegisteredTimeBetween() {
        // Given
        Long memberId = 0L;
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2023, 3, 1), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2023, 3, 3), LocalTime.MIN);
        Pageable pageable = Pageable.unpaged();

        // When
        Page<Delivery> deliveryList = deliveryRepository.findByMemberIdAndRegisteredTimeBetween(memberId, start, end, pageable);

        // Then
        Assertions.assertThat(deliveryList.getSize()).isEqualTo(3);
    }

    private static Delivery getMockInstance(int dayOfMonth) {
        LocalDateTime registeredTime = LocalDateTime.of(LocalDate.of(2023, 3, dayOfMonth), LocalTime.MIN);
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
        Long memberId = 0L;

        return Delivery.builder()
                .registeredTime(registeredTime)
                .address(address)
                .memberId(memberId)
                .build();
    }

}