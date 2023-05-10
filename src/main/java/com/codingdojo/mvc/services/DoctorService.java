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
	
	
	 //get Doctor by id 
    public Doctor getDoctorById(Long id) {
    	Optional<Doctor> optionalDoctor = doctorRepo.findById(id);
 			if(optionalDoctor.isPresent()) {
 				return optionalDoctor.get();
 			}else {
 				return null;
 		}
    }
    
 // retrieves a Doctor 
    public Doctor findDoctor(Long id) {
       Optional<Doctor> optional = doctorRepo.findById(id);
        if(optional.isPresent()) {
           return optional.get();
        } else {
            return null;
        }
    }
    
//count the num of all doctors
//    public List<Doctor> count(){
//    	return doctorRepo.CountDoctor();
//    }

	public void checkAppoint( Doctor doctor) {
		Doctor chosenDoc = doctor;
		
		
	}

}
