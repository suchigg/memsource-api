package it.lucafarsetti.memsource.account;

import java.util.UUID;

public class Saved {

	private final UUID id;
	public Saved(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

}
