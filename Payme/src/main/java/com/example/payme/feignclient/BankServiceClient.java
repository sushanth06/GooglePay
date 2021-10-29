package com.example.payme.feignclient;

import java.util.List;

import com.example.payme.dto.AccountDto;
import com.example.payme.dto.PaymeFundTransferDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


//@FeignClient(name = "http://ORDER-SERVICE/order/orders")

@FeignClient(value="bank-service", url="http://localhost:8082/demo/")
public interface BankServiceClient {
	
	@GetMapping("/users/port")
	public String getPortNo();

	@GetMapping("/users/account/{phoneNumber}")
	public AccountDto getAccountByPhoneNumber(@PathVariable String phoneNumber);

	@PostMapping("/fundTransfer/payMe")
	public void intiatePaymeFundTransfer(@RequestBody PaymeFundTransferDto fundTransferDto);

}