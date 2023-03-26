package com.barogo.delivery.app.delivery.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class DeliveryAddressHistoryAttributeConverter implements AttributeConverter<List<? super DeliveryAddress>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<? super DeliveryAddress> attribute) {
        return OBJECT_MAPPER.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public List<? super DeliveryAddress> convertToEntityAttribute(String dbData) {
        String unwrapped = StringUtils.unwrap(dbData, "\"");
        String unescaped = StringEscapeUtils.unescapeJava(unwrapped);
        return OBJECT_MAPPER.readValue(unescaped, new TypeReference<>() {
        });
    }

}
