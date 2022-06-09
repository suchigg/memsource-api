package it.lucafarsetti.memsource.projects;

import java.util.List;

public class Projects {

	private int pageNumber;
	private int numberOfElements;
	private int totalElements;
	private int pageSize;
	private int totalPages;
	private List<Project> content;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Project> getContent() {
		return content;
	}

	public void setContent(List<Project> content) {
		this.content = content;
	}

}
