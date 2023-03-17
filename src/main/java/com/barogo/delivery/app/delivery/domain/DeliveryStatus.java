package com.barogo.delivery.app.delivery.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {

    REGISTERED, // 접수
    ASSIGNED, // 배차
    DELIVERY_START, // 배달 시작
    DELIVERY_END; // 배달 완료

}
