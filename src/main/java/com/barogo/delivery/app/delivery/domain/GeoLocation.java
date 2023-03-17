package com.barogo.delivery.app.delivery.domain;

import com.barogo.delivery.util.AssertUtils;
import com.barogo.delivery.util.DistanceUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeoLocation {

    private double latitude;
    private double longitude;

    @Builder
    public GeoLocation(double latitude, double longitude) {
        AssertUtils.AssertNull(latitude, longitude);

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getDistanceFrom(GeoLocation other) {
        return DistanceUtils.distance(
                latitude, other.latitude,
                longitude, other.longitude,
                "K"
        );
    }

}
