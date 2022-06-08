package it.lucafarsetti.memsource.projects;

import java.util.List;
import java.util.Locale;

public class Project{

	private List<Locale> targetLangs;
	private Locale sourceLang;
	private String name;
	private String status;

	public List<Locale> getTargetLangs() {
		return targetLangs;
	}

	public void setTargetLangs(List<Locale> targetLangs) {
		this.targetLangs = targetLangs;
	}

	public Locale getSourceLang() {
		return sourceLang;
	}

	public void setSourceLang(Locale sourceLang) {
		this.sourceLang = sourceLang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}


