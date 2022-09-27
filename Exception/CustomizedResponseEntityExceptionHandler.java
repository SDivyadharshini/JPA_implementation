package com.example.jdbc.project_JPA.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
@ExceptionHandler(Exception.class)	
public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
	
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	ResponseEntity<Object>responseEntity =  new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	return responseEntity;
	
}

@ExceptionHandler(RoomNotFoundException.class)	
public final ResponseEntity<Object> handleRoomNotFoundExceptions(RoomNotFoundException ex, WebRequest request) {
	
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	ResponseEntity<Object>responseEntity =  new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	return responseEntity;
	
}

@ExceptionHandler(RoomAlreadyExistsException.class)	
public final ResponseEntity<Object> handleRoomAlreadyExistsExceptions(RoomAlreadyExistsException ex, WebRequest request) {
	
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	ResponseEntity<Object>responseEntity =  new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	return responseEntity;
	
}

/*@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
	return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
}
*/
}
