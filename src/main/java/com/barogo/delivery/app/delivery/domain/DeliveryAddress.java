package com.barogo.delivery.app.delivery.domain;

import com.barogo.delivery.util.AssertUtils;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress {
    private String postCode;
    private String address;
    private String addressDetail;
    @Embedded
    private GeoLocation geoLocation;

    @Builder
    public DeliveryAddress(String postCode, String address, String addressDetail, GeoLocation geoLocation) {
        AssertUtils.AssertNull(postCode, address, addressDetail, geoLocation);

        this.postCode = postCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.geoLocation = geoLocation;
    }

    public double getDistanceFrom(DeliveryAddress other) {
        return geoLocation.getDistanceFrom(other.getGeoLocation());
    }

}
