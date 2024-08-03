package com.sreekanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
