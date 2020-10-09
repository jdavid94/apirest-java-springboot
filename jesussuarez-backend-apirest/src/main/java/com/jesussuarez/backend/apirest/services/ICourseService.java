package com.jesussuarez.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.jesussuarez.backend.apirest.models.entity.Course;


public interface ICourseService {
	
	public List<Course> findAll();
	public Page<Course> find(Pageable pageable);
	public Course findBy(Long id);
	public Course save(Course course);
	public void delete(Long id);

}
