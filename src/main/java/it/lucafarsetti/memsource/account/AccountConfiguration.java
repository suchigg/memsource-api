package it.lucafarsetti.memsource.account;

import javax.validation.constraints.NotEmpty;

public class AccountConfiguration {

	@NotEmpty
	private final String userName;

	@NotEmpty
	private final String password;

	public AccountConfiguration() {
		this.userName = "";
		this.password = "";
	}

	public AccountConfiguration(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
