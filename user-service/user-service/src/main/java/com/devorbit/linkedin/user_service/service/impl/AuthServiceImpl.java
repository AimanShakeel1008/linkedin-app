package com.devorbit.linkedin.user_service.service.impl;

import com.devorbit.linkedin.user_service.dto.LoginRequestDto;
import com.devorbit.linkedin.user_service.dto.SignupRequestDto;
import com.devorbit.linkedin.user_service.dto.UserDto;
import com.devorbit.linkedin.user_service.entity.User;
import com.devorbit.linkedin.user_service.exception.BadRequestException;
import com.devorbit.linkedin.user_service.exception.ResourceNotFoundException;
import com.devorbit.linkedin.user_service.repository.UserRepository;
import com.devorbit.linkedin.user_service.service.AuthService;
import com.devorbit.linkedin.user_service.service.JWTService;
import com.devorbit.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final JWTService jwtService;
	@Override
	public UserDto signUp(SignupRequestDto signupRequestDto) {
		User user = modelMapper.map(signupRequestDto, User.class);
		user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public String login(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByEmail(loginRequestDto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with email: "+loginRequestDto.getEmail()));

		boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), user.getPassword());

		if(!isPasswordMatch) {
			throw new BadRequestException("Incorrect Password!");
		}

		return jwtService.generateAccessToken(user);
	}
}
