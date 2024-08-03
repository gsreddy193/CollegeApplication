package com.sreekanth.dto;

import com.sreekanth.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
	private String userName;
	private String password;
	private Role role;

}
