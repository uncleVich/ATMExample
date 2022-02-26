package com.example.atmserver.services;

import com.example.atmserver.repositories.DepositRepository;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public void depositAmount(long cardId, double amount) {
        depositRepository.depositAmount(cardId, amount);
    }
}
