package com.jesussuarez.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesussuarez.backend.apirest.models.dao.IStudentDao;
import com.jesussuarez.backend.apirest.models.entity.Student;

@Service //Service component in Spring
public class StudentServiceImplement implements IStudentService {
	
	@Autowired 
	private IStudentDao studentDao; // Dependency Injection in Spring

	@Override
	@Transactional(readOnly = true )
	public List<Student> findAll() {
		return studentDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true )
	public Page<Student> find(Pageable pageable) {		
		return studentDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true )
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
