package com.example.payme.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
public class PaymeFundTransfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromPhoneNumber;

    private String toPhoneNumber;

    private int amountToTransfer;

    private LocalDateTime transactionDate  = LocalDateTime.now();

    private String description;

    public PaymeFundTransfer(String fromPhoneNumber, String toPhoneNumber, int amountToTransfer, String description) {
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.amountToTransfer = amountToTransfer;
        this.description = description;
    }

    public PaymeFundTransfer() {
        super();
    }
}

