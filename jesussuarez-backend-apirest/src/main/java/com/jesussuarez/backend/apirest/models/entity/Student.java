package com.jesussuarez.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="students")
public class Student implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Incremental ID for MYSQL
	private Long id;
		
	private String rut;
	private String name;
	@Column(name="last_name")
	private String lastName;
	
	
	private int age;
	
	@NotNull(message="Cannot be Empty")
	@ManyToOne(fetch=FetchType.LAZY) // Indicate many to one relationship in DataBase - FetchType LAZY
	@JoinColumn(name="course_id") // Key that generates the relationship between tables.
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Annotation to work with lazy and avoid problems
	private Course course;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
