package com.sreekanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
