package it.lucafarsetti.memsource.account;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class AccountConfiguration {

	@NotEmpty
	private final String username;

	@NotEmpty
	private final String password;

	public AccountConfiguration() {
		this.username = "";
		this.password = "";
	}

	public AccountConfiguration(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
