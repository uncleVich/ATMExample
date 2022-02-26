package com.example.atmserver.repositories;

import com.example.atmserver.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Balance getBalanceByCardId(long cardId);

    @Modifying
    @Transactional
    @Query(value = "update balance set balance_amount =:amount, last_updated = now() where card_id =:cardId", nativeQuery = true)
    void updateBalance(long cardId, double amount);

}
