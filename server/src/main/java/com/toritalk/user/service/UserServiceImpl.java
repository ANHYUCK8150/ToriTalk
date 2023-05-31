package com.toritalk.user.service;

import com.toritalk.user.entity.User;
import com.toritalk.user.entity.UserPrincipal;
import com.toritalk.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserById(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
		return UserPrincipal.create(user);
	}
}
