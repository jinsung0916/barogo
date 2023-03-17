package com.barogo.delivery.app.delivery.domain;

public interface ModifyAddressCondition {

    boolean isModifiable(Delivery delivery, DeliveryAddress newAddress);

}
