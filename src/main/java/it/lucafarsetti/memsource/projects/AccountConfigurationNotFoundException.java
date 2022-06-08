package it.lucafarsetti.memsource.projects;

public class AccountConfigurationNotFoundException extends RuntimeException {

	public AccountConfigurationNotFoundException() {
		super("Unable to find the account configuration");
	}

}
