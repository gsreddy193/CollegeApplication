package com.sreekanth.dto;

import com.sreekanth.entity.Role;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class RegisterRequest {
	private String userName;
	private String password;
	private Role role;
	private String name;
	private String email;
	private String phone;
	private Long departmentId;
	private String year;
	private String officeHours;

}
