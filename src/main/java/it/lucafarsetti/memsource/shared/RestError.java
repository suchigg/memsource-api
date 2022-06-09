package it.lucafarsetti.memsource.shared;

public class RestError {

	private String exception;
	private String message;

	public RestError(String exception, String message) {
		this.exception = exception;
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}

}
