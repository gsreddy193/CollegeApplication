package com.sreekanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
