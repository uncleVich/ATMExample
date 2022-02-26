package com.example.atmserver.services;

import com.example.atmserver.repositories.WithdrawalRepository;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    public WithdrawalService(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    public void withdrawalAmount(long cardId, double amount) {
        withdrawalRepository.withdrawalAmount(cardId, amount);
    }

    public Double getDailyWithdrawalAmount(long cardId) {
        return withdrawalRepository.getDailyWithdrawalAmount(cardId);
    }
}
