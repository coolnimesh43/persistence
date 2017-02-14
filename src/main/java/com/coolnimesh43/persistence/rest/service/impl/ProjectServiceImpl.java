package com.coolnimesh43.persistence.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolnimesh43.persistence.constant.PersistenceConstant;
import com.coolnimesh43.persistence.entity.Project;
import com.coolnimesh43.persistence.exception.EntityNotFoundException;
import com.coolnimesh43.persistence.mapper.ProjectMapper;
import com.coolnimesh43.persistence.repository.ProjectRepository;
import com.coolnimesh43.persistence.rest.dto.ProjectDTO;
import com.coolnimesh43.persistence.rest.service.ProjectService;
import com.google.inject.Inject;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private ProjectMapper projectMapper;

    @Override
    public ProjectDTO findOne(Long id) {
        if (id != null) {
            Project project = this.projectRepository.findOne(id);
            if (project != null) {
                return this.projectMapper.projectToProjectDTO(project);
            }
        }
        return null;
    }

    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        if (projectDTO != null) {
            Project project = this.projectMapper.projectDTOToProject(projectDTO);
            project = this.projectRepository.save(project);
            return this.projectMapper.projectToProjectDTO(project);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Project project = this.projectRepository.findOne(id);
            if (project != null) {
                project.setStatus(PersistenceConstant.Status.DELETED);
                this.projectRepository.save(project);
            } else {
                throw new EntityNotFoundException("Project with given id not found");
            }
        }
    }

    @Override
    public List<ProjectDTO> findByName(String name) {
        if (name != null) {
            List<Project> projects = this.projectRepository.findByName(name);
            if (projects != null && !projects.isEmpty()) {
                return this.projectMapper.projectsToProjectDTOs(projects);
            }
        }
        return new ArrayList<>();
    }

}
