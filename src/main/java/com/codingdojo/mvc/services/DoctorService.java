package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.mvc.model.Doctor;
import com.codingdojo.mvc.model.User;
import com.codingdojo.mvc.repository.DoctorRepository;
import com.codingdojo.mvc.request.UserLogin;
@Service
public class DoctorService {
	@Autowired
	DoctorRepository doctorRepo;

	public List<Doctor> getAllDoctor(){
		return doctorRepo.findAll();
	}
}
