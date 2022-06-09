package it.lucafarsetti.memsource.shared;

import it.lucafarsetti.memsource.projects.AccountConfigurationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountConfigurationNotFoundException.class)
	public ResponseEntity<RestError> handleError(AccountConfigurationNotFoundException e) {
		final RestError body = new RestError(e.getClass().getSimpleName(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<FieldValidationErrors> handleError(MethodArgumentNotValidException e) {
		List<FieldValidationError> errorDetails = e.getBindingResult().getFieldErrors()
												   .stream()
												   .map(error ->
														  new FieldValidationError(error.getField(),
																				   error.getDefaultMessage()))
												   .collect(Collectors.toList());

		final FieldValidationErrors body = new FieldValidationErrors(errorDetails);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestError> handleError(RuntimeException e) {
		final RestError body = new RestError(e.getClass().getSimpleName(), e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

}
