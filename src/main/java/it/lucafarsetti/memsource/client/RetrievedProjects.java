package it.lucafarsetti.memsource.client;

import java.util.List;

public class RetrievedProjects {

	private int pageNumber;
	private int numberOfElements;
	private int totalElements;
	private int pageSize;
	private int totalPages;
	private List<RetrievedProject> content;

	public int getPageNumber() {
		return pageNumber;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<RetrievedProject> getContent() {
		return content;
	}

}
