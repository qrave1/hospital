package org.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.example.model.Appointment;


@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	
	List<Appointment> findAll();
	
	
	
}
