package com.toritalk.user.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toritalk.common.dto.PageResponse;
import com.toritalk.user.dto.UserResponse;
import com.toritalk.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;

	@GetMapping("/")
	public ResponseEntity<PageResponse<UserResponse>> getUsers(Pageable pageable) {
		return ResponseEntity.ok(userService.getUsers(pageable));
	}
}
