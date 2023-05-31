package com.toritalk.user.dto;

import lombok.Data;

@Data
public class UserRequest {
	public Long id;
	public String name;
	public String password;
	public String imageUrl;
	public String introduction;
}
