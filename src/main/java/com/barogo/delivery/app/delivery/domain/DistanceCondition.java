package com.barogo.delivery.app.delivery.domain;

import com.barogo.delivery.util.AssertUtils;

public class DistanceCondition implements ModifyAddressCondition {

    private final double maxDistance;

    public DistanceCondition(double maxDistance) {
        AssertUtils.AssertNull(maxDistance);

        this.maxDistance = maxDistance;
    }

    @Override
    public boolean isModifiable(Delivery delivery, DeliveryAddress newAddress) {
        DeliveryAddress oldAddress = delivery.getAddress();
        double distance = oldAddress.getDistanceFrom(newAddress);

        return distance <= maxDistance;
    }

}
