package com.barogo.delivery.app.delivery.infra;

import com.barogo.delivery.app.delivery.domain.*;
import com.barogo.delivery.app.delivery.domain.DeliveryManagerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MockDeliveryManagerRepository implements DeliveryManagerRepository {

    @Override
    public Optional<DeliveryManager> findOne() {
        return Optional.of(
                () -> new AndModifyCondition(
                        new DeliveryStatusCondition(DeliveryStatus.REGISTERED),
                        new DistanceCondition(5),
                        new MaxCountCondition(2)
                )
        );
    }

}
