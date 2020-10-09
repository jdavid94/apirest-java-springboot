package com.jesussuarez.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;



import com.jesussuarez.backend.apirest.models.entity.Student;

public interface IStudentDao extends JpaRepository<Student, Long> {

}
