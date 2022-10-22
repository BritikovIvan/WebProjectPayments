package com.webproject.model.entity.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<BankAccountStatus, String> {
    @Override
    public String convertToDatabaseColumn(BankAccountStatus accountStatus) {
        if (accountStatus == null) {
            return null;
        }
        return accountStatus.getCode();
    }

    @Override
    public BankAccountStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(BankAccountStatus.values())
                .filter(accountStatus -> accountStatus.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
