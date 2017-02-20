package com.coolnimesh43.persistence.rest.service;

import java.util.List;

import com.coolnimesh43.persistence.entity.ProjectMember;
import com.coolnimesh43.persistence.rest.dto.ProjectMemberDTO;

public interface ProjectMemberService extends AbstractService<ProjectMemberDTO, Long> {

    /**
     * Find by project id and user id.
     * 
     * @author coolnimesh43
     * @param projectId
     *            {@link Long} project id.
     * @param userId
     *            {@link Long} user id
     * @return {@link ProjectMember}
     */
    ProjectMemberDTO findByProjectIdAndUserId(Long projectId, Long userId);

    /**
     * Find all {@link ProjectMember}s by project id and status.
     * 
     * @author coolnimesh43
     * @param projectId
     *            {@link Long} project id.
     * @param status
     *            {@link String} status
     * @return {@link List} {@link ProjectMemberDTO}
     */
    List<ProjectMemberDTO> findByProjectIdAndStatus(Long projectId, String status);

    /**
     * Find project member by given project id, userId and status.
     * 
     * @author coolnimesh43
     * @param projectId
     *            {@link Long} Project id
     * @param userId
     *            {@link Long} User id
     * @param status
     *            {@link String} the status.
     * @return {@link ProjectMemberDTO}
     */
    ProjectMemberDTO findOneByProjectIdAndUserIdAndStatus(Long projectId, Long userId, String status);

    ProjectMemberDTO findOneByProjectIdAndUserLoginAndStatus(Long projectId, String login, String status);

    /**
     * Get all projects assigned to given user.
     * 
     * @author coolnimesh43
     * @param userId
     *            {@link Long} The user's id that belongs to the projects.
     * @return {@link List} of {@link ProjectMemberDTO} that the user belongs to.
     */
    List<ProjectMemberDTO> findByUserId(Long userId);
}
