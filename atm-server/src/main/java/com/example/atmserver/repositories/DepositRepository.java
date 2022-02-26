package com.example.atmserver.repositories;

import com.example.atmserver.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into deposit(amount, last_updated, card_id) values (:amount, now(), :cardId)", nativeQuery = true)
    void depositAmount(@Param("cardId") long cardId, @Param("amount") double amount);
}
