package com.jesussuarez.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesussuarez.backend.apirest.models.entity.Course;
import com.jesussuarez.backend.apirest.services.ICourseService;


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
	public Page<Course> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return courseService.find(pageable);			
	}
}
