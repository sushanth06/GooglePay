package com.example.payme.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.payme.dto.AccountDto;
import com.example.payme.dto.UserDto;
import com.example.payme.feignclient.BankServiceClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.example.payme.entity.User;
import com.example.payme.repository.UserRepository;
import com.example.payme.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	BankServiceClient bankServiceClient;

//	@Autowired
//	CircuitBreakerFactory circuitBreakerFactory;

	@Override
	public User addUser(UserDto userDto){
		User user = convertUserDtoToEntity(userDto);
		validateUserData(user);
		User savedUserData = userRepository.save(user);
		return user;
	}

	@Override
	public String getBankServicePortNo() {
		return bankServiceClient.getPortNo();
	}

	
	@Override
	public List<UserDto> getAllUsers(){
		return userRepository.findAll()
				.stream()
				.map(this::convertUserEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto convertUserEntityToDto(User user) {
		UserDto userDto = new UserDto(user.getFirstName(),user.getLastName(),user.getPhone());
		return  userDto;
	}

	public User convertUserDtoToEntity(UserDto userDto) {
		User user = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getPhone());
		return  user;
	}
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	private void validateUserData(User user) throws IllegalArgumentException {
		AccountDto accountDto = bankServiceClient.getAccountByPhoneNumber(user.getPhone());
        if (user.getFirstName() == null || user.getFirstName().length() == 0) {
            throw new IllegalArgumentException("First Name is mandatory");
        } else  if (user.getLastName() == null || user.getLastName().length() == 0) {
            throw new IllegalArgumentException("Last Name is mandatory");
        } else if(accountDto.getAccountID() == null) {
			throw new IllegalArgumentException("Phone number doesn't match with any existing account, Please link you mobile number to your account");
		}
    }
}
 

