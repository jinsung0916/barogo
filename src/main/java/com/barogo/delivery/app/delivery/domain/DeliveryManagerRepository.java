package com.barogo.delivery.app.delivery.domain;

import java.util.Optional;

public interface DeliveryManagerRepository {

    Optional<DeliveryManager> findOne();

}
