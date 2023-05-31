package com.toritalk.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toritalk.common.dto.PageResponse;
import com.toritalk.user.dto.UserResponse;
import com.toritalk.user.entity.User;
import com.toritalk.user.entity.UserPrincipal;
import com.toritalk.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserById(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
		return UserPrincipal.create(user);
	}

	@Override
	public PageResponse<UserResponse> getUsers(Pageable pageable) {
		Page<UserResponse> pageInfo = userRepository.findAll(pageable).map(UserResponse::from);
		return PageResponse.<UserResponse>builder()
			.contents(pageInfo.getContent())
			.pageNumber(pageInfo.getNumber())
			.pageSize(pageInfo.getSize())
			.totalPages(pageInfo.getTotalPages())
			.totalElements(pageInfo.getTotalElements())
			.build();
	}
}
