package com.toritalk.user.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.toritalk.common.dto.PageResponse;
import com.toritalk.user.dto.UserRequest;
import com.toritalk.user.dto.UserResponse;
import com.toritalk.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;

	@GetMapping
	public ResponseEntity<PageResponse<UserResponse>> getUsers(Pageable pageable) {
		return ResponseEntity.ok(userService.getUsers(pageable));
	}

	@PostMapping
	public ResponseEntity<UserResponse> setUser(UserRequest request, @RequestBody MultipartFile imageFile) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.setUser(request, imageFile));
	}
}
