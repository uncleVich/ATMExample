package com.example.atmserver.repositories;

import com.example.atmserver.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card getCardByCardNumberAndPin(String cardNumber, int pin);

    @Modifying
    @Transactional
    @Query(value = "update card set pin =:newPin where id =:id", nativeQuery = true)
    int updateCardPin(int id, int newPin);

}
