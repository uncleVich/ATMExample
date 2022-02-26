package com.example.atmserver.controllers;

import com.example.atmserver.model.Balance;
import com.example.atmserver.services.BalanceService;
import com.example.atmserver.services.DepositService;
import com.example.atmserver.services.WithdrawalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm/card")
public class OperationController {

    public static final double WITHDRAWAL_LIMIT = 50_000;

    private final BalanceService balanceService;
    private final DepositService depositService;
    private final WithdrawalService withdrawalService;

    public OperationController(BalanceService balanceService, DepositService depositService, WithdrawalService withdrawalService) {
        this.balanceService = balanceService;
        this.depositService = depositService;
        this.withdrawalService = withdrawalService;
    }

    @GetMapping("/{cardId}/balance")
    public double getClientBalance(@PathVariable long cardId) {

        Balance balance = balanceService.getCardBalance(cardId);

        if (balance == null) {
            try {
                throw new Exception("Карта не найдена");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assert balance != null;
        return balance.getBalanceAmount();
    }

    @PostMapping("/{cardId}/withdraw")
    public String withdrawAmount(@PathVariable long cardId, @RequestParam(name = "amount") double amount) {

        Double dailyAmount = withdrawalService.getDailyWithdrawalAmount(cardId);

        if (dailyAmount == null) {
            dailyAmount = 0.0;
        }
        if (dailyAmount + amount > WITHDRAWAL_LIMIT) {
            return "Превышен дневной лимит снятия";
        }

        double currentBalance = balanceService.getCardBalance(cardId).getBalanceAmount();

        if (currentBalance < amount) {
            return "Недостаточно средств для снятия";
        }

        withdrawalService.withdrawalAmount(cardId, amount);
        balanceService.updateCardBalance(cardId, currentBalance - amount);

        return "Операция успешно завершена";
    }

    @PostMapping("/{cardId}/deposit")
    public String depositAmount(@PathVariable long cardId, @RequestParam(name = "amount") double amount) {
        double currentBalance = balanceService.getCardBalance(cardId).getBalanceAmount();
        depositService.depositAmount(cardId, amount);
        balanceService.updateCardBalance(cardId, amount + currentBalance);

        return "Операция успешно завершена";
    }

}
