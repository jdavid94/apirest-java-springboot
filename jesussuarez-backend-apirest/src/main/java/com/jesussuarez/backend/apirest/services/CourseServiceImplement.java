package com.jesussuarez.backend.apirest.services;

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
	public List<Course> findAll() {		
		return (List<Course>) courseDao.findAll();
	}

	@Override
	@Transactional(readOnly = true )
	public Page<Course> find(Pageable pageable) {
		return courseDao.findAll(pageable);
	}

	@Override
	public Course findBy(Long id) {
		return courseDao.findById(id).orElse(null);
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
