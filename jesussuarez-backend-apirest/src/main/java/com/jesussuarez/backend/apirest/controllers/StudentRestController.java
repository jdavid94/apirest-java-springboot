package com.jesussuarez.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesussuarez.backend.apirest.models.entity.Student;
import com.jesussuarez.backend.apirest.models.services.IStudentService;
import com.jesussuarez.backend.apirest.validators.StudentValidator;



@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	@Autowired
	private IStudentService studenService; // Dependency Injection in Spring
	
	/** GET ALL STUDENTS  */
	@GetMapping("/students/all")
	public List<Student> index() {
		return studenService.findAll();		
	}
	
	/** GET ALL STUDENTS PAGEABLE */
	@GetMapping("/students")
	public Page<Student> index2() {
		Pageable pageable = PageRequest.of(0, 2);
		return studenService.find(pageable);			
	}
	
	/** GET STUDENT BY ID */	
	@GetMapping("/students/{id}")	
	public ResponseEntity<?> show(@PathVariable Long id) {	// ResponseEntity and any type to handle errors response	
		Student student = studenService.findById(id);
		Map<String, Object> response = new HashMap<>(); // Used to stores Object or Values
		if (student == null) { // Student Null Validation
			response.put("message", "Student with ID : " .concat(id.toString().concat(" doesn't exist"))); // If student doesn't exist return status 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);			
	}
	
	
	/** POST CREATE NEW STUDENT  */	
	@PostMapping("/students")	
	public ResponseEntity<?> create(@Valid @RequestBody Student student, BindingResult result) { //RequesBody - JSON format - Intercepter - We validate each value received in the request body
		Student newStudent = null;
		Map<String, Object> response = new HashMap<>(); // Used to stores Object or Values
		new StudentValidator().validate(student, result); // Import the Validation for Student RUT
		if (result.hasErrors()) { // If body has Errors
			response.put("errors", "Invalid Data - Check Age > 18 and Valid Rut");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); // If data invalid return status 400
		}		
		try {
			newStudent = studenService.save(student);
;		} catch (DataAccessException e) {
			response.put("message", "Error with DataBase Insert Query");		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // If data invalid return status 500
		}	
		response.put("message", "Student created Successfully");
		response.put("Student", newStudent);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); // Return status 201 Successfully 
	}
	
	/** PUT UPDATE STUDENT  */	
	@PutMapping("/students/{id}")	
	public Student update(@RequestBody Student student, @PathVariable Long id) { //RequesBody - JSON format
		Student currentStudent = studenService.findById(id); // Find the Student that receive
		currentStudent.setRut(student.getRut());	
		currentStudent.setName(student.getName());
		currentStudent.setLastName(student.getLastName());
		currentStudent.setAge(student.getAge());
		currentStudent.setCourse(student.getCourse());
		return studenService.save(currentStudent);
	}
			
	/** DELETE STUDENT  */	
	@DeleteMapping("/students/{id}")		
	public ResponseEntity<?> delete(@PathVariable Long id) { // ResponseEntity and any type to handle errors response	
		Map<String, Object> response = new HashMap<>(); // Used to stores Object or Values
		try {			
			studenService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Student with ID : " .concat(id.toString().concat(" doesn't exist"))); // If Student doesn't exist return status 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); // If Student doesn't exist return status 404
		}
		response.put("message", "Student Deleted Successfully");	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
