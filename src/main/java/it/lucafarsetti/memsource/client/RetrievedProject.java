package it.lucafarsetti.memsource.client;

import java.util.List;
import java.util.Locale;

public class RetrievedProject {

	private List<Locale> targetLangs;
	private Locale sourceLang;
	private String name;
	private String status; // ENUM

	public List<Locale> getTargetLangs() {
		return targetLangs;
	}

	public Locale getSourceLang() {
		return sourceLang;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

}
