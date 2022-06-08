package it.lucafarsetti.memsource.shared;

import java.util.List;

public class FieldValidationErrors {

	private List<FieldValidationError> errors;

	public FieldValidationErrors(List<FieldValidationError> errors) {
		this.errors = errors;
	}

	public List<FieldValidationError> getErrors() {
		return errors;
	}

}
