package com.example.payme.service;

import com.example.payme.dto.PaymeFundTransferDto;
import com.example.payme.entity.PaymeFundTransfer;
import org.springframework.beans.factory.annotation.Autowired;

public interface PaymeFundTransferService {
	
	@Autowired
	public PaymeFundTransferDto transferFund(PaymeFundTransferDto fundTransferDto);
}
