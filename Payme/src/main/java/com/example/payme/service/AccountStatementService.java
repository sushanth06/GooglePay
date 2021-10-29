package com.example.payme.service;

import java.util.List;

import com.example.payme.dto.PaymeFundTransferDto;
import com.example.payme.entity.PaymeFundTransfer;
import org.springframework.beans.factory.annotation.Autowired;

public interface AccountStatementService {

	@Autowired
	public List<PaymeFundTransferDto> getAccountStatementFromPhoneNumber(String phoneNumber,int pageNumber, int pageSize);

}
