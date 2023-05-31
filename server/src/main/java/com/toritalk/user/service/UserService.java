package com.toritalk.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.toritalk.common.dto.PageResponse;
import com.toritalk.user.dto.UserRequest;
import com.toritalk.user.dto.UserResponse;

public interface UserService {
	UserDetails loadUserById(Long userId);

	PageResponse<UserResponse> getUsers(Pageable pageable);

	UserResponse setUser(UserRequest request, MultipartFile imageFile);
}
