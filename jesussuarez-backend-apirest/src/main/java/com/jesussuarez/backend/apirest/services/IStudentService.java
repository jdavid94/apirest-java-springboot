package com.jesussuarez.backend.apirest.services;

import java.util.List;



import com.jesussuarez.backend.apirest.models.entity.Student;



public interface IStudentService {
	
	public List<Student> findAll();
	public Student findById(Long id);
	public Student save(Student student);
	public void delete(Long id);

}
