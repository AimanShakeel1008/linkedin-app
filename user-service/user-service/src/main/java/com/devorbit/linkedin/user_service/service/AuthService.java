package com.devorbit.linkedin.user_service.service;

import com.devorbit.linkedin.user_service.dto.LoginRequestDto;
import com.devorbit.linkedin.user_service.dto.SignupRequestDto;
import com.devorbit.linkedin.user_service.dto.UserDto;

public interface AuthService {
	UserDto signUp(SignupRequestDto signupRequestDto);

	String login(LoginRequestDto loginRequestDto);
}
