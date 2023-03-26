package com.barogo.delivery.app.delivery.controller;

import com.barogo.delivery.app.delivery.domain.Delivery;
import com.barogo.delivery.app.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public Page<Delivery> get(
            @Valid GetListCommand command,
            Pageable pageable
    ) {
        return deliveryService.getList(command.getMemberId(), command.getStart(), command.getEnd(), pageable);
    }

    @PutMapping("/{id}/address")
    public Delivery updateAddress(
            @PathVariable Long id,
            @RequestBody @Valid UpdateAddressCommand command
    ) {
        return deliveryService.updateAddress(id, command.toEntity());
    }

}
