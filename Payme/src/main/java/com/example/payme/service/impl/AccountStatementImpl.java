package com.example.payme.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.example.payme.dto.PaymeFundTransferDto;
import com.example.payme.entity.PaymeFundTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.payme.repository.PaymeFundTransferRepository;
import com.example.payme.service.AccountStatementService;

@Service
public class AccountStatementImpl implements AccountStatementService {
	@Autowired
	PaymeFundTransferRepository fundTransferRepository;


	@Override
	public List<PaymeFundTransferDto> getAccountStatementFromPhoneNumber(String phoneNumber,int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		List<PaymeFundTransfer> paymeFundTransferList = fundTransferRepository.findByFromPhoneNumberOrToPhoneNumberAllIgnoreCase(phoneNumber,phoneNumber,pageable);
		List<PaymeFundTransferDto> paymeFundTransferDtoList = paymeFundTransferList.stream().map(this::convertFundTransferEntityToDto).collect(Collectors.toList());
		return  paymeFundTransferDtoList;
	}

	public PaymeFundTransferDto convertFundTransferEntityToDto(PaymeFundTransfer fundTransfer) {
		PaymeFundTransferDto fundTransferDto = new PaymeFundTransferDto(fundTransfer.getFromPhoneNumber(),fundTransfer.getToPhoneNumber(),fundTransfer.getAmountToTransfer(),fundTransfer.getDescription());
		return  fundTransferDto;
	}
}

