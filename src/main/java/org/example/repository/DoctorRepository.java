package org.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.example.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long>{
	List<Doctor> findAll();
}
