package it.lucafarsetti.memsource.projects;

import it.lucafarsetti.memsource.client.RetrievedProjects;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectsMapper {

	Projects from(RetrievedProjects retrievedProjects);

}
