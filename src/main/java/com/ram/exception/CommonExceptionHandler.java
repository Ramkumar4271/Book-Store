package com.ram.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ram.model.APIResponse;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<APIResponse> handleCommonException(Exception e,  WebRequest wr){
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(APIResponse
						.builder()
						.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.error(e.getMessage())
						.build());
	}
	
	@ExceptionHandler
	public final ResponseEntity<APIResponse> handleConstraintViolationException(DataIntegrityViolationException e,  WebRequest wr){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(APIResponse
						.builder()
						.status(HttpStatus.BAD_REQUEST)
						.error("could not execute statement due to Constraint Violation!")
						.build());
	}
	
	@ExceptionHandler
	public final ResponseEntity<APIResponse> handleNotValidException(MethodArgumentNotValidException e,  WebRequest wr){
		
		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		Iterator<ObjectError> itr = errors.iterator();
		List<String> errorMessages = new ArrayList<>();
		
		while(itr.hasNext()) {
			errorMessages.add(itr.next().getDefaultMessage());
		}
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(
						APIResponse
						.builder()
						.status(HttpStatus.BAD_REQUEST)
						.error(errorMessages)
						.build());
	}
}
