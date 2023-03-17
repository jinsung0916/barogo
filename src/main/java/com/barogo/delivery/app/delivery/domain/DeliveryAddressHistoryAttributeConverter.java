package com.barogo.delivery.app.delivery.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;
import java.util.Objects;

@Converter
public class DeliveryAddressHistoryAttributeConverter implements AttributeConverter<List<? super DeliveryAddress>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<? super DeliveryAddress> attribute) {
        return mapper.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public List<? super DeliveryAddress> convertToEntityAttribute(String dbData) {
        String processed = dbData.replaceAll("^\"|\"$", "");
        return mapper.readValue(processed, new TypeReference<>() {
        });
    }

}
