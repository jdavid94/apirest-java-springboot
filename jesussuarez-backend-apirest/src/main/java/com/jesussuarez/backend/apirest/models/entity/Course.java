package com.jesussuarez.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Incremental ID for MYSQL
	private Long id;
	private String name;
	
	@Size(max=4) //Max 4 chars validation
	private int code;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
