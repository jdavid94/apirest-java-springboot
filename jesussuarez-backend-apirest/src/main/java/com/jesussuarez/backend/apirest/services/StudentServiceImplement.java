package com.jesussuarez.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesussuarez.backend.apirest.models.dao.IStudentDao;
import com.jesussuarez.backend.apirest.models.entity.Student;

@Service //Service component in Spring
public class StudentServiceImplement implements IStudentService {
	
	@Autowired 
	private IStudentDao studentDao; // Dependency Injection in Spring

	@Override
	public List<Student> findAll() {
		return studentDao.findAll();
	}

	@Override
	public Student findById(Long id) {
		return studentDao.findById(id).orElse(null);		
	}

	@Override
	public Student save(Student student) {
		return studentDao.save(student);
	}

	@Override
	public void delete(Long id) {
		studentDao.deleteById(id);
	}

	

}
