package it.lucafarsetti.memsource.account;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class Account {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

}
