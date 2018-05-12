package com.heyu.framework.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.heyu.framework.exception.CommonException;

public class ValidatorUtil {

	public static Validator validator;
	
	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	public static void validateEntity(Object object) throws CommonException {
		Set<ConstraintViolation<Object>> sets= validator.validate(object);
		if(!sets.isEmpty()) {
			ConstraintViolation<Object> constraint = sets.iterator().next();
			throw new CommonException(constraint.getMessage());
		}
	}
}
