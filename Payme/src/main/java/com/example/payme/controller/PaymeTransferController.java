package com.example.payme.controller;


import com.example.payme.dto.PaymeFundTransferDto;
import com.example.payme.entity.PaymeFundTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payme.service.PaymeFundTransferService;

@RestController
@RequestMapping("/fundTransfer")
public class PaymeTransferController {

	@Autowired
	PaymeFundTransferService fundTransferService ;
	
	@PostMapping
	public PaymeFundTransferDto transferFund(@RequestBody PaymeFundTransferDto fundTransferDto) {
		return fundTransferService.transferFund(fundTransferDto);
	}
 
}
