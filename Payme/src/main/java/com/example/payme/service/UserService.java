package com.example.payme.service;

import java.util.List;

import com.example.payme.dto.UserDto;
import com.example.payme.entity.User;

public interface UserService {

	public User addUser(UserDto userDto);

	public List<UserDto> getAllUsers();

	public User getUserById(Long id);

	public void deleteUserById(Long id);

	public UserDto convertUserEntityToDto(User user);

	public String getBankServicePortNo();
}
