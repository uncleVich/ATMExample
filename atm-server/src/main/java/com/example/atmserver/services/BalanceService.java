package com.example.atmserver.services;

import com.example.atmserver.model.Balance;
import com.example.atmserver.repositories.BalanceRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public Balance getCardBalance(long cardId) {
        return balanceRepository.getBalanceByCardId(cardId);
    }

    public void updateCardBalance(long cardId, double amount) {
        balanceRepository.updateBalance(cardId, amount);
    }
}
