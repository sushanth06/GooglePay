package com.example.payme.dto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
	@NotNull
	@NotEmpty
	private String firstName;
	private String lastName;
	private String phone;

	public UserDto(String firstName, String lastName, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}

}
