package com.barogo.delivery.app.delivery.domain;

import com.barogo.delivery.util.AssertUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private LocalDateTime registeredTime;
    private LocalDateTime assignedTime;
    private LocalDateTime deliveryStartTime;
    private LocalDateTime deliveryEndTime;

    @Embedded
    private DeliveryAddress address;
    @Column(columnDefinition = "json")
    @Convert(converter = DeliveryAddressHistoryAttributeConverter.class)
    private List<DeliveryAddress> addressHistory;

    private Long memberId;
    private Long riderId;

    @Builder
    public Delivery(LocalDateTime registeredTime, DeliveryAddress address, Long memberId) {
        AssertUtils.AssertNull(registeredTime, address, memberId);

        this.deliveryStatus = DeliveryStatus.REGISTERED;
        this.registeredTime = registeredTime;
        this.memberId = memberId;
        setAddress(address);
    }

    private void setAddress(DeliveryAddress address) {
        this.address = address;

        if(Objects.isNull(addressHistory)) {
            addressHistory = new ArrayList<>();
        }

        this.addressHistory.add(address);
    }

    public void assign(LocalDateTime assignTime, Long riderId) {
        this.deliveryStatus = DeliveryStatus.ASSIGNED;
        this.assignedTime = assignTime;
        this.riderId = riderId;
    }

    public void cancelAssign(LocalDateTime cancelTime) {
        this.deliveryStatus = DeliveryStatus.REGISTERED;
        this.assignedTime = cancelTime;
    }

    public void start(LocalDateTime deliveryStartTime) {
        this.deliveryStatus = DeliveryStatus.DELIVERY_START;
        this.deliveryStartTime = deliveryStartTime;
    }

    public void end(LocalDateTime deliveryEndTime) {
        this.deliveryStatus = DeliveryStatus.DELIVERY_END;
        this.deliveryEndTime = deliveryEndTime;
    }

    public void changeAddress(ModifyAddressCondition condition, DeliveryAddress address) {
        if (!condition.isModifiable(this, address)) {
            throw new IllegalStateException();
        }

        setAddress(address);
    }

}
