package com.webproject.service.impl;

import com.webproject.model.entity.CreditCard;
import com.webproject.model.repository.CreditCardRepository;
import com.webproject.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository repository;

    @Override
    public CreditCard findCardById(Long id) {
        return repository.findById(id).get();
    }
}
