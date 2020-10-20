package com.uday.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<ErrorResponse> entityNotFound(EntityNotFoundException exception) {		
		return errorResponse(exception);
	}
	


	@ExceptionHandler({ JsonParseException.class })
	public ResponseEntity<ErrorResponse> methodArgumentMismatched(MethodArgumentTypeMismatchException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getName() + " should be of type "
				+ exception.getRequiredType().getName() + " " + exception.getMostSpecificCause().getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResponse.setReason("Invalid argument type");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ErrorResponse> constrainViolationException(ConstraintViolationException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getConstraintViolations().stream()
				.map(c -> c.getPropertyPath() + " " + c.getMessage()).collect(Collectors.joining()));

		errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResponse.setReason("Constraint Violation");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> errorResponse(BaseException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(exception.getHttpStatus().getReasonPhrase());
		errorResponse.setReason(exception.getUserMessage());
		errorResponse.setMessage(exception.getSystemMessage());
		return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
	}

	
	
	@Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	       ErrorResponse apiError = new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.name(), ex.getRootCause().getMessage());
	       return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	   }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {		
		String code = HttpStatus.BAD_REQUEST.getReasonPhrase();
		List<ErrorResponse> errorList = new ArrayList<>(1);
		Map<String, String> fieldByMessage = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
		if (!fieldByMessage.entrySet().isEmpty()) {
			//return ResponseEntity.badRequest().body(fieldByMessage);
			errorList.add(new ErrorResponse(fieldByMessage.toString(),ex.getMessage(), code));
			return ResponseEntity.badRequest().body(errorList);
		} else {
			
			errorList.add(new ErrorResponse(ex.getLocalizedMessage(), code));
			return ResponseEntity.badRequest().body(errorList);
		}
	}

	
	
	
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse internalError(Exception exception) {
		System.out.println("internalserver");
		String code = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
		return new ErrorResponse(exception.getLocalizedMessage(), code);
	}
}