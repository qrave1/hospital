package org.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.User;
import org.example.services.AppointmentService;
import org.example.services.DoctorService;
import org.example.services.UserService;


@Controller
public class MainController {
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	DoctorService doctorService;

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String home() {
	
		return "index.jsp";
	}
	@RequestMapping("/index")
	public String appointment(HttpSession session, Model model) {
		if (session.getAttribute("user_id")==null) {
			return "redirect:/reg";
		}
		Long id= (Long) session.getAttribute("user_id");
		User user =userService.findUser(id);
		model.addAttribute("user", user);
		List<Doctor> allDoctor = doctorService.getAllDoctor();
		model.addAttribute("allDoctor", allDoctor);
		model.addAttribute("doctors", doctorService.getAllDoctor());
		List<Appointment> all = appointmentService.getAllAppointment();
		model.addAttribute("all", all);
		return"app.jsp";
	}

	@PostMapping("/index")
	public String newBook(@Valid @ModelAttribute("appointment") Appointment appointment, Model model,HttpSession session) {
		if (session.getAttribute("user_id")==null) {
			return "redirect:/reg";
		}

			model.addAttribute("user", userService.findUser((Long) session.getAttribute("user_id")));

			appointmentService.createAppointment(appointment);


			return String.format("redirect:/check/%s", appointment.getId());

	}
	
	
	@GetMapping("/check/{appoId}")
	 public String newpage(@PathVariable("appoId")Long appoId, Model model,HttpSession session) {  
		// for Error checking i use @valid 
		if (session.getAttribute("user_id")==null) {
			return "redirect:/reg";
		}
		if(!model.containsAttribute("timee")) {
			model.addAttribute("timee" , new Appointment());
		}
		
			Appointment appo = appointmentService.findAppointment(appoId);


			model.addAttribute("appo", appo);

	          return "check.jsp";
	      
	  }
	
	@PutMapping("/checkTime/{id}/edit")
    public String updateBook(@Valid @ModelAttribute("timee") Appointment timee,BindingResult result,RedirectAttributes redirectAttributes,HttpSession session,
    		@PathVariable("id") Long id, Model model) {
		if (session.getAttribute("user_id")==null) {
			return "redirect:/reg";
		}
		else if(result.hasErrors()) {
			model.addAttribute("timee", timee);
			redirectAttributes.addFlashAttribute("timee", timee);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.timee", result);

			return "check.jsp";
		}
		
		
    	appointmentService.updateAppointment(id, timee);                   // update(id ,book);
    	redirectAttributes.addFlashAttribute("success" ,"appointment has been creaated");
    	return "redirect:/userAppo";

    }
	
	
	@RequestMapping("/userAppo")
	public String userAppo(HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
    		return "redirect:/index";
    	}
		Long id= (Long) session.getAttribute("user_id");
		User userid =userService.findUser(id);
		model.addAttribute("userid", userid);
		
		List<Appointment> all = appointmentService.getAllAppointment();
		model.addAttribute("all", all);
		
		
		return"user.jsp";
	}


	@GetMapping("/edit/{id}")
	public String edit(Model model,@PathVariable("id")Long id,HttpSession session) {
		if(session.getAttribute("user_id")== null) {
			return "redirect:/";
		}
		model.addAttribute("appointment", appointmentService.findAppointment(id));
		return "edit.jsp";
	}
	
	@PutMapping("/edit/{id}")
	public String updateApp(@Valid @ModelAttribute("appointment") Appointment appointment,@PathVariable("id")Long id, BindingResult result) {
		if(result.hasErrors()) 
			return "edit.jsp";
		
		else {
			System.out.println(id);
			appointmentService.updateAppointment(id,appointment);
			return "redirect:/userAppo";
		}
	}
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		appointmentService.deleteAppointment(id);
		return "redirect:/userAppo";
	}
}