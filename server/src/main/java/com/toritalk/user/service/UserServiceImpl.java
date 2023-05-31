package com.toritalk.user.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.toritalk.common.component.StorageUploader;
import com.toritalk.common.dto.FileUploadResponse;
import com.toritalk.common.dto.PageResponse;
import com.toritalk.user.dto.UserRequest;
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
	private final PasswordEncoder pwdEncoder;
	private final StorageUploader storageUploader;

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

	@Override
	@Transactional
	public UserResponse setUser(UserRequest request, MultipartFile imageFile) {
		if (userRepository.existsByName(request.getName())) {
			throw new IllegalArgumentException("이미 존재하는 이름입니다.");
		}

		if (imageFile != null) {
			try {
				FileUploadResponse fileUploadResponse = storageUploader.upload(imageFile, "users");
				request.setImageUrl(fileUploadResponse.getUrl());
			} catch (IOException e) {
			}
		}

		return userRepository.findById(
				userRepository.save(
					User.builder()
						.id(request.getId())
						.name(request.getName())
						.imageUrl(request.getImageUrl())
						.introduction(request.getIntroduction())
						.password(pwdEncoder.encode(request.getPassword()))
						.build()
				).getId()
			).map(UserResponse::from)
			.orElseThrow(() -> new IllegalArgumentException("저장 실패"));
	}
}
