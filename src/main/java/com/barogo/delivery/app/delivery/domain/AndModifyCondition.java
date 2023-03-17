package com.barogo.delivery.app.delivery.domain;

import java.util.List;

public class AndModifyCondition implements ModifyAddressCondition {

    private final List<ModifyAddressCondition> conditions;

    public AndModifyCondition(ModifyAddressCondition... conditions) {
        this.conditions = List.of(conditions);
    }

    @Override
    public boolean isModifiable(Delivery delivery, DeliveryAddress newAddress) {
        return conditions.stream().allMatch(it -> it.isModifiable(delivery, newAddress));
    }

}
