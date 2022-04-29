
package com.project.clinic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.clinic.entity.Patient;

import com.project.clinic.service.PatientService;

@Controller//connect the frond end and backend
public class MedicinePatientController {

	@Autowired
	private PatientService pservice;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/Register")
	public String addPatientForm() {
		return "addPatient";
	}

	@PostMapping("/addPatients")
	public String patientSave(  @ModelAttribute Patient p, HttpSession session) {
	
		System.out.println(p);
		pservice.addPatient(p);
		session.setAttribute("msg", "Data added successfully..");
		return "redirect:/RegisteredPatient";
	}

	@GetMapping("/RegisteredPatient")
	public String patientView(Model m) {
		List<Patient> pat = pservice.getAllPatient();
		m.addAttribute("patient", pat);
		return "viewPatient";
	}

	@GetMapping("/Edit/{pid}")
	public String patientEdit(@PathVariable String pid, Model m2) {

		Patient p = pservice.getPatientById(pid);
		m2.addAttribute("patient", p);
		return "updatePatient";
	}

	@PostMapping("/updatePatients")
	public String patientUpdate(@ModelAttribute Patient p, HttpSession session) {

		pservice.addPatient(p);
		session.setAttribute("msg", "Data updated successfully..");
		return "redirect:/RegisteredPatient";
	}

	@GetMapping("/Drop/{pid}")
	public String medDelete(@PathVariable String pid, HttpSession session) {
		pservice.deletePatient(pid);
		session.setAttribute("msg", "Data deleted successfully..");
		return "redirect:/RegisteredPatient";
	}


}
