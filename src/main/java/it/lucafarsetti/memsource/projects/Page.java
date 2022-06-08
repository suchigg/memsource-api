package it.lucafarsetti.memsource.projects;

public class Page {

	private Integer pageNumber;

	private Integer pageSize;

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		int page = pageNumber == null ? 0 : pageNumber;
		return Math.max(page, 0);
	}

	public int getPageSize() {
		int size = pageSize == null || pageSize <= 0 ? 50 : pageSize;
		return Math.min(size, 1000);
	}

}
