package com.barogo.delivery.app.delivery.service;

import com.barogo.delivery.app.delivery.domain.Delivery;
import com.barogo.delivery.app.delivery.domain.DeliveryAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Validated
public interface DeliveryService {

    Page<Delivery> getList(@NotNull LocalDateTime start, @NotNull LocalDateTime end, @NotNull Pageable pageable);

    @PostAuthorize("#authentication.name == returnObject.memberId")
    Delivery updateAddress(@NotNull Long deliveryId, @NotNull DeliveryAddress newAddress);

}
