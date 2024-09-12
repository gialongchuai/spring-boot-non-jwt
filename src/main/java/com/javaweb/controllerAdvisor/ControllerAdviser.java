package com.javaweb.controllerAdvisor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customException.FieldException;
import com.javaweb.model.ErrorResponse;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handelArithmeticException(ArithmeticException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Loi chia cho 0 nguy hiem");
		details.add("ban phai fix no ngay");
		errorResponse.setDetails(details);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FieldException.class)
	public ResponseEntity<Object> handelFieldException(FieldException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Khong duoc de noi dung trong long!");
		details.add("ban phai fix no ngay");
		errorResponse.setDetails(details);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
	}
}
