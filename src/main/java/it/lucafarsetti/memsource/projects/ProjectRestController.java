package it.lucafarsetti.memsource.projects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectRestController {

	public ProjectRestController(ProjectService projectService) {
		this.projectService = projectService;
	}

	private final ProjectService projectService;

	@GetMapping
	public ResponseEntity<Projects> findAll(@RequestParam(required = false) Integer pageNumber,
												 @RequestParam(required = false) Integer pageSize) {

		Projects projects = this.projectService.findAll();
		return ResponseEntity.ok(projects);

	}

	private int getPageNumber(Integer page) {
		page = page == null ? 0 : page;
		return Math.max(page, 0);
	}

	private int getPageSize(Integer size) {
		size = size == null || size <= 0 ? 10 : size;
		return Math.min(size, 100);
	}


}
