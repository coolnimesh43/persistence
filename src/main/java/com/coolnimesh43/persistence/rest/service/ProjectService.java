package com.coolnimesh43.persistence.rest.service;

import java.util.List;

import com.coolnimesh43.persistence.rest.dto.ProjectDTO;

public interface ProjectService extends AbstractService<ProjectDTO, Long> {

    /**
     * Find all projects by given name.
     * 
     * @author coolnimesh43
     * @param name
     *            {@link String} name
     * @return {@link List} of {@link ProjectDTO}
     */
    List<ProjectDTO> findByName(String name);

}
