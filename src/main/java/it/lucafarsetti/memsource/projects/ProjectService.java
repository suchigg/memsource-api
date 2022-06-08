package it.lucafarsetti.memsource.projects;

import it.lucafarsetti.memsource.client.MemsourceClient;
import it.lucafarsetti.memsource.client.RetrievedProjects;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


	private final MemsourceClient memsourceClient;
	private final ProjectsMapper projectsMapper;

	public ProjectService(MemsourceClient memsourceClient, ProjectsMapper projectsMapper) {
		this.memsourceClient = memsourceClient;
		this.projectsMapper = projectsMapper;
	}

	public Projects findAll(Page page) {
		RetrievedProjects retrievedProjects = memsourceClient.findAllProjects(page);
		return projectsMapper.from(retrievedProjects);
	}

}
