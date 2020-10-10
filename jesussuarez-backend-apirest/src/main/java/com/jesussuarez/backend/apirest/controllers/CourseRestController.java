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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jesussuarez.backend.apirest.models.entity.Course;
import com.jesussuarez.backend.apirest.models.services.ICourseService;




@RestController
@RequestMapping("/api")
public class CourseRestController {
	
	@Autowired
	private ICourseService courseService; // Dependency Injection in Spring
	
	/** GET ALL COURSE  */
	@GetMapping("/courses/all")
	public List<Course> index() {
		return courseService.findAll();		
	}
	
	/** GET ALL COURSE PAGEABLE */
	@GetMapping("/courses")
	public Page<Course> index2() {
		Pageable pageable = PageRequest.of(0, 2);
		return courseService.find(pageable);			
	}
	
	/** GET COURSE BY ID */	
	@GetMapping("/courses/{id}")	
	public ResponseEntity<?> show(@PathVariable Long id) {	// ResponseEntity and any type to handle errors response	
		Course course = courseService.findById(id);
		Map<String, Object> response = new HashMap<>(); // Used to stores Object or Values
		if (course == null) { // Course Null Validation
			response.put("message", "Course with ID : " .concat(id.toString().concat(" doesn't exist"))); // If course doesn't exist return status 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);			
	}
	
	/** POST CREATE NEW COURSE  */	
	@PostMapping("/courses")  
	@ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<?> create(@Valid @RequestBody Course course, BindingResult result) { //RequesBody - JSON format - Intercepter - We validate each value received in the request body
		Course newCourse = null;
		Map<String, Object> response = new HashMap<>(); // Used to stores Object or Values
		if (result.hasErrors()) { // If body has Errors
			response.put("errors", "Invalid Data");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); // If data invalid return status 400
		}		
		try {
			newCourse = courseService.save(course)
;		} catch (DataAccessException e) {
			response.put("message", "Error with DataBase Insert Query");		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // If data invalid return status 500
		}	
		response.put("message", "Course created Successfully");
		response.put("Course", newCourse);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); // Return status 201 Successfully 
	}
	
	/** PUT UPDATE NEW COURSE  */	
	@PutMapping("/courses/{id}")		
	public Course update(@RequestBody Course course, @PathVariable Long id) { //RequesBody - JSON format
		Course currentCourse = courseService.findById(id); // Find the course that receive
		currentCourse.setCode(course.getCode());
		currentCourse.setName(course.getName());		
		return courseService.save(currentCourse);
	}
	
	/** DELETE COURSE  */	
	@DeleteMapping("/courses/{id}")		
	public ResponseEntity<?> delete(@PathVariable Long id) { // ResponseEntity and any type to handle errors response	
		Map<String, Object> response = new HashMap<>(); // Used to stores Object or Values
		try {			
			courseService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Course with ID : " .concat(id.toString().concat(" doesn't exist"))); // If course doesn't exist return status 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); // If Course doesn't exist return status 404
		}
		response.put("message", "Course Deleted Successfully");	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}	
}
