package com.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeRecordNotFound.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(EmployeeRecordNotFound ex) 
	{
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.setErrorMessage(ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleConnException(Exception ex) 
	{
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.setErrorMessage(ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}