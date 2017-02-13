package com.coolnimesh43.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.coolnimesh43.persistence.entity.Project;
import com.coolnimesh43.persistence.rest.dto.ProjectDTO;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mappings(value = { @Mapping(target = "parentProjectId", source = "parentProject.id") })
    ProjectDTO projectToProjectDTO(Project project);

    List<ProjectDTO> projectsToProjectDTOs(List<Project> project);

    @Mappings(value = { @Mapping(source = "parentProjectId", target = "parentProject") })
    Project projectDTOToProject(ProjectDTO projectDTO);

    List<Project> projectDTOsToProjects(List<Project> projects);

    default Project map(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }
}
