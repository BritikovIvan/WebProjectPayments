package com.web_project.model.entity.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CardStatusConverter implements AttributeConverter<CreditCardStatus, String> {
    @Override
    public String convertToDatabaseColumn(CreditCardStatus cardStatus) {
        if (cardStatus == null) {
            return null;
        }
        return cardStatus.getCode();
    }

    @Override
    public CreditCardStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(CreditCardStatus.values())
                .filter(cardStatus -> cardStatus.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
