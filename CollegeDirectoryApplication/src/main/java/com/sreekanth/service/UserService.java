package com.sreekanth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sreekanth.dto.RegisterRequest;
import com.sreekanth.entity.AdministratorProfile;
import com.sreekanth.entity.FacultyProfile;
import com.sreekanth.entity.StudentProfile;
import com.sreekanth.entity.User;
import com.sreekanth.jwt.JwtUtil;
import com.sreekanth.repository.AdministratorProfileRepository;
import com.sreekanth.repository.DepartmentRepository;
import com.sreekanth.repository.FacultyProfileRepository;
import com.sreekanth.repository.StudentProfileRepository;
import com.sreekanth.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentProfileRepository studentProfileRepository;
	@Autowired
	private FacultyProfileRepository facultyProfileRepository;
	@Autowired
	private AdministratorProfileRepository administratorProfileRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public User authenticate(String userName, String password) {
		User user = userRepository.findByUserName(userName);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}
	
	public User register(RegisterRequest registerRequest) throws Exception {
		if (userRepository.findByUserName(registerRequest.getUserName()) != null) {
			throw new Exception("Username already exists");
		}
		if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
			throw new Exception("Email already exists");
		}
		User user = new User();
		user.setUserName(registerRequest.getUserName());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setRole(registerRequest.getRole());
		user.setName(registerRequest.getName());
		user.setEmail(registerRequest.getEmail());
		user.setPhone(registerRequest.getPhone());
		
		User savedUser =  userRepository.save(user);
		
		switch (registerRequest.getRole()) {
		case STUDENT: 
			StudentProfile studentProfile = new StudentProfile();
			studentProfile.setUser(savedUser);
			studentProfile.setDepartment(departmentRepository.findById(registerRequest.getDepartmentId()).orElse(null));
			studentProfile.setYear(registerRequest.getYear());
			studentProfileRepository.save(studentProfile);
			break;
		case FACULTY_MEMBER:
			FacultyProfile facultyProfile = new FacultyProfile();
			facultyProfile.setUser(savedUser);
			facultyProfile.setDepartment(departmentRepository.findById(registerRequest.getDepartmentId()).orElse(null));
			facultyProfile.setOfficeHours(registerRequest.getOfficeHours());
			facultyProfileRepository.save(facultyProfile);
			break;
		case ADMINISTRATOR:
			AdministratorProfile administratorProfile = new AdministratorProfile();
			administratorProfile.setUser(savedUser);
			administratorProfile.setDepartment(departmentRepository.findById(registerRequest.getDepartmentId()).orElse(null));
			administratorProfileRepository.save(administratorProfile);
			break;
		}
		return savedUser;
	}

	public String generateToken(User user) {
		return jwtUtil.generateToken(user);
	}

	public User loadUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}


}
