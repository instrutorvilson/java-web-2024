package com.agenda.excpetion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.agenda.records.MsgErro;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<?>> handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<MsgErro> errorString = new ArrayList<>();
		result.getFieldErrors().forEach(error -> errorString.add( new MsgErro(error.getField(), error.getDefaultMessage())));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorString);
	}
}


