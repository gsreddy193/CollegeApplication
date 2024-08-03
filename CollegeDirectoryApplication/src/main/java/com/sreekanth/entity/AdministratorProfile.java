package com.sreekanth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AdministratorProfile {
	@Id
	private Long userId;
	private String photo;
	private Integer departmentId;
	@OneToOne
	@MapsId
	private User user;
	@ManyToOne
	private Department department;

}
