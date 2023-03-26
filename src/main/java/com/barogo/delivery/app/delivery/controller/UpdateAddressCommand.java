package com.barogo.delivery.app.delivery.controller;

import com.barogo.delivery.app.delivery.domain.DeliveryAddress;
import com.barogo.delivery.app.delivery.domain.GeoLocation;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateAddressCommand {

    @NotBlank
    private String postCode;
    @NotBlank
    private String address;
    @NotBlank
    private String addressDetails;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    public DeliveryAddress toEntity() {
        return DeliveryAddress.builder()
                .postCode(postCode)
                .address(address)
                .addressDetail(addressDetails)
                .geoLocation(
                        GeoLocation.builder()
                                .latitude(latitude)
                                .longitude(longitude)
                                .build()
                )
                .build();
    }

}
