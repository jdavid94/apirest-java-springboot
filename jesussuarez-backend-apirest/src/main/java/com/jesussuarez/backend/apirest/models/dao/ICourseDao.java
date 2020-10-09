package com.jesussuarez.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;



import com.jesussuarez.backend.apirest.models.entity.Course;

public interface ICourseDao extends JpaRepository<Course, Long> {

}
