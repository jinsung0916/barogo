package com.barogo.delivery.app.delivery.domain;

import com.barogo.delivery.util.AssertUtils;

public class DeliveryStatusCondition implements ModifyAddressCondition {

    private final DeliveryStatus theStatus;

    public DeliveryStatusCondition(DeliveryStatus theStatus) {
        AssertUtils.AssertNull(theStatus);

        this.theStatus = theStatus;
    }

    @Override
    public boolean isModifiable(Delivery delivery, DeliveryAddress newAddress) {
        return theStatus.equals(delivery.getDeliveryStatus());
    }

}

