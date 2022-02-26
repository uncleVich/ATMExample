package com.example.atmserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balanceAmount;
    private LocalDate lastUpdated;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardId")
    private Card card;
}
