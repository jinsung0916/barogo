package com.barogo.delivery.app.delivery.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Page<Delivery> findByRegisteredTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

}
