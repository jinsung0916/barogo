package com.barogo.delivery.app.delivery.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaxCountCondition implements ModifyAddressCondition {

    private final int maxCount;

    @Override
    public boolean isModifiable(Delivery delivery, DeliveryAddress newAddress) {
        return delivery.getAddressHistory().size() < maxCount;
    }

}
