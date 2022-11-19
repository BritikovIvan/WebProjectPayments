package com.web_project.processor;

import com.web_project.model.entity.CreditCard;
import com.web_project.model.entity.enums.CreditCardStatus;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class CreditCardProcessor implements ItemProcessor<CreditCard, CreditCard> {

    @Override
    public CreditCard process(CreditCard creditCard) throws Exception {
        if (creditCard.getValidity().isBefore(LocalDate.now())) {
            creditCard.setStatus(CreditCardStatus.EXPIRED);
        }
        return creditCard;
    }
}
