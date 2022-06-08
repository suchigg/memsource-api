package it.lucafarsetti.memsource.account;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Saved {

	private final UUID id;
	public Saved(UUID id) {
		this.id = id;
	}

}
