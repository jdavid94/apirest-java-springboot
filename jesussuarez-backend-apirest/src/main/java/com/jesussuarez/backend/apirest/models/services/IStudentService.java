package com.jesussuarez.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.jesussuarez.backend.apirest.models.entity.Student;



public interface IStudentService {
	
	public List<Student> findAll();
	public Page<Student> find(Pageable pageable);
	public Student findById(Long id);
	public Student save(Student student);
	public void delete(Long id);

}
