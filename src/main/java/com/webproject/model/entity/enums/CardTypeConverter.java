package com.webproject.model.entity.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CardTypeConverter implements AttributeConverter<CreditCardType, String> {
    @Override
    public String convertToDatabaseColumn(CreditCardType cardType) {
        if (cardType == null) {
            return null;
        }
        return cardType.getCode();
    }

    @Override
    public CreditCardType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(CreditCardType.values())
                .filter(cardType -> cardType.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
