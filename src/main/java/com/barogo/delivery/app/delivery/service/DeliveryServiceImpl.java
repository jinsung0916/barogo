package com.barogo.delivery.app.delivery.service;

import com.barogo.delivery.app.delivery.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryManagerRepository deliveryManagerRepository;
    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)
    public Page<Delivery> getList(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return deliveryRepository.findByRegisteredTimeBetween(start, end, pageable);
    }

    public Delivery updateAddress(Long deliveryId, DeliveryAddress newAddress) {
        DeliveryManager manager = deliveryManagerRepository.findOne()
                .orElseThrow();

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow();

        delivery.changeAddress(manager.getModifyAddressCondition(), newAddress);

        return delivery;
    }

}
