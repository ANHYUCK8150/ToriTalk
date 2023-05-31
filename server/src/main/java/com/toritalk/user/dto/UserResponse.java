package com.toritalk.user.dto;

import com.toritalk.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserResponse {
	public Long id;
	public String name;
	public String imageUrl;
	public String introduction;

	@Builder
	public UserResponse(Long id, String name, String imageUrl, String introduction) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.introduction = introduction;
	}

	public static UserResponse from(User user) {
		return UserResponse.builder()
			.id(user.getId())
			.name(user.getName())
			.imageUrl(user.getImageUrl())
			.introduction(user.getIntroduction())
			.build();
	}
}
