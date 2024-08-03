package com.sreekanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.entity.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

}
