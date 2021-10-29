package com.example.payme.controller;

import java.util.List;

import com.example.payme.dto.PaymeFundTransferDto;
import com.example.payme.entity.PaymeFundTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.payme.service.AccountStatementService;
 
@RestController
@RequestMapping("/accountStatement")
public class AccountStatmentController {
	
	
	@Autowired
	AccountStatementService accountStatementService;
	
	@GetMapping("/{phoneNumber}")
	public List<PaymeFundTransferDto> getAccountStatementFromID(@PathVariable String phoneNumber, @RequestParam int pageNumber,@RequestParam int pageSize) {
		return accountStatementService.getAccountStatementFromPhoneNumber(phoneNumber,pageNumber,pageSize);
	}
 
}