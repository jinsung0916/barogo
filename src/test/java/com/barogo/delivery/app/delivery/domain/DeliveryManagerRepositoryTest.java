package com.barogo.delivery.app.delivery.domain;

import com.barogo.delivery.app.delivery.infra.MockDeliveryManagerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= MockDeliveryManagerRepository.class)
class DeliveryManagerRepositoryTest {

    @Autowired
    private DeliveryManagerRepository deliveryManagerRepository;

    @Test
    void findOne() {
        // When
        DeliveryManager deliveryManager = deliveryManagerRepository.findOne().orElse(null);

        // Then
        Assertions.assertThat(deliveryManager).isNotNull();
    }

}