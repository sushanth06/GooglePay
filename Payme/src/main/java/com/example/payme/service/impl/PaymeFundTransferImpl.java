package com.example.payme.service.impl;

import com.example.payme.dto.AccountDto;
import com.example.payme.dto.PaymeFundTransferDto;
import com.example.payme.entity.PaymeFundTransfer;
import com.example.payme.feignclient.BankServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payme.entity.User;
import com.example.payme.repository.PaymeFundTransferRepository;
import com.example.payme.repository.UserRepository;
import com.example.payme.service.PaymeFundTransferService;

import java.util.Optional;

@Service
public class PaymeFundTransferImpl implements PaymeFundTransferService {
    @Autowired
    PaymeFundTransferRepository fundTransferRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankServiceClient bankServiceClient;


	/*
			1. Update local fund transfer
			2. Update Bank app Fund transfer
			3. Update Bank app phone transfer
			3. Update  Bank app account balances of sender and recipient
	 */

    public PaymeFundTransferDto transferFund(PaymeFundTransferDto fundTransferDto) {
        PaymeFundTransfer paymeFundTransferFromDto = new PaymeFundTransfer(fundTransferDto.getFromPhoneNumber(), fundTransferDto.getToPhoneNumber(), fundTransferDto.getAmountToTransfer(),fundTransferDto.getDescription());
        validateAndMakeTransaction(paymeFundTransferFromDto);
        PaymeFundTransfer paymeFundTransfer = fundTransferRepository.save(paymeFundTransferFromDto);
        updateBankAppData(paymeFundTransfer);
        return fundTransferDto;
    }

    private void validateAndMakeTransaction(PaymeFundTransfer fundTransfer) throws IllegalArgumentException {
        // first check if from and to accountids exists
        // Check if balance is available
        // change balance in both the account -> add/remove
        String fromPhoneNumber = fundTransfer.getFromPhoneNumber();
        String toPhoneNumber = fundTransfer.getToPhoneNumber();
        long amountToTransfer = fundTransfer.getAmountToTransfer();


        AccountDto fromAccountDto = bankServiceClient.getAccountByPhoneNumber(fromPhoneNumber);
        AccountDto toAccountDto = bankServiceClient.getAccountByPhoneNumber(toPhoneNumber);

        if (fromAccountDto.getAccountID() == null && toAccountDto.getAccountID() == null) {
            throw new IllegalArgumentException("Both Sender phone number and Recipient phone number are not linked to any accounts,Please double check");
        } else if (fromAccountDto.getAccountID() == null) {
            throw new IllegalArgumentException("Sender phone number is not linked to any account");
        } else if (toAccountDto.getAccountID() == null) {
            throw new IllegalArgumentException("Sender phone number is not linked to any account");
        } else if (fromAccountDto.getAccountBalance() < amountToTransfer) {
            throw new IllegalArgumentException("Funds are not sufficient to make this transaction,Please load funds");
        } else if (amountToTransfer == 0) {
            throw new IllegalArgumentException("Please add amount greater than zero to make a transaction");
        }
    }

    public void updateBankAppData(PaymeFundTransfer paymeFundTransfer) {
        PaymeFundTransferDto paymeFundTransferDto = new PaymeFundTransferDto(paymeFundTransfer.getFromPhoneNumber(), paymeFundTransfer.getToPhoneNumber(), paymeFundTransfer.getAmountToTransfer(), paymeFundTransfer.getDescription());
        bankServiceClient.intiatePaymeFundTransfer(paymeFundTransferDto);
    }

}
