package com.jesussuarez.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesussuarez.backend.apirest.models.dao.ICourseDao;
import com.jesussuarez.backend.apirest.models.entity.Course;

@Service //Service component in Spring
public class CourseServiceImplement implements ICourseService {
	
	
	@Autowired 
	private ICourseDao courseDao; // Dependency Injection in Spring
	
	@Override
	@Transactional(readOnly = true )
	public List<Course> findAll() {		
		return (List<Course>) courseDao.findAll();
	}

	@Override
	@Transactional(readOnly = true )
	public Page<Course> find(Pageable pageable) {
		return courseDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true )
	public Course findById(Long id) {
		return courseDao.findById(id).orElse(null); // If doesn't exist, return null	
	}

	@Override
	public Course save(Course course) {
		return courseDao.save(course);
	}

	@Override
	public void delete(Long id) {
		courseDao.deleteById(id);
		
	}

}
