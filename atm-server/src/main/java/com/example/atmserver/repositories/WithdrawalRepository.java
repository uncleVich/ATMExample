package com.example.atmserver.repositories;

import com.example.atmserver.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

    @Query(value = "select sum(amount) as total_amount from withdrawal where card_id = :cardId and CAST(last_updated AS DATE) = CURRENT_DATE", nativeQuery = true)
    Double getDailyWithdrawalAmount(long cardId);

    @Modifying
    @Transactional
    @Query(value = "insert into withdrawal(amount, last_updated, card_id) values (:amount, now(), :cardId)", nativeQuery = true)
    void withdrawalAmount(@Param("cardId") long cardId, @Param("amount") double amount);
}
