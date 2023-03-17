package com.barogo.delivery.app.delivery.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeliveryManagerTest {

    @Test
    @DisplayName("배달 주소 수정 조건을 조회한다.")
    void getModifyAddressCondition() {
        // Given
        DeliveryManager deliveryManager = AndModifyCondition::new;

        // When
        ModifyAddressCondition condition = deliveryManager.getModifyAddressCondition();

        // Then
        Assertions.assertThat(condition).isNotNull();
    }

}
