package com.jesussuarez.backend.apirest.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jesussuarez.backend.apirest.models.entity.Student;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {		
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		Student student = (Student) target;
		String rut = student.getRut();
		// RUT VALIDATION
		boolean flag = false;
	    try {
	        rut =  rut.toUpperCase();
	        rut = rut.replace(".", "");
	        rut = rut.replace("-", "");
	        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

	        char dv = rut.charAt(rut.length() - 1);

	        int m = 0, s = 1;
	        for (; rutAux != 0; rutAux /= 10) {
	            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
	        }
	        if (dv == (char) (s != 0 ? s + 47 : 75)) {
	        	flag = true;
	        }
	    } catch (java.lang.NumberFormatException e) {
	    	
	    } catch (Exception e) {
	    	
	    }
	    if (!flag) {
	    	errors.rejectValue("Rut" , "Invalid Rut");
	    	return;
	    }
	}

}
