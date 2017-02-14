package com.coolnimesh43.persistence.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolnimesh43.persistence.constant.PersistenceConstant;
import com.coolnimesh43.persistence.entity.ProjectMember;
import com.coolnimesh43.persistence.exception.EntityNotFoundException;
import com.coolnimesh43.persistence.mapper.ProjectMemberMapper;
import com.coolnimesh43.persistence.repository.ProjectMemberRepository;
import com.coolnimesh43.persistence.rest.dto.ProjectMemberDTO;
import com.coolnimesh43.persistence.rest.service.ProjectMemberService;
import com.google.inject.Inject;

@Service
@Transactional(readOnly = true)
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Inject
    private ProjectMemberRepository projectMemberRepository;

    @Inject
    private ProjectMemberMapper projectMemberMapper;

    @Override
    public ProjectMemberDTO findOne(Long id) {
        if (id != null) {
            ProjectMember projectMember = this.projectMemberRepository.findOne(id);
            if (projectMember != null) {
                return this.projectMemberMapper.projectMemberToProjectMemberDTO(projectMember);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public ProjectMemberDTO save(ProjectMemberDTO projectMemberDTO) {
        if (projectMemberDTO != null) {
            ProjectMember member = this.projectMemberMapper.projectMemberDTOToProjectMember(projectMemberDTO);
            member = this.projectMemberRepository.save(member);
            if (member != null) {
                return this.projectMemberMapper.projectMemberToProjectMemberDTO(member);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id != null) {
            ProjectMember projectMember = this.projectMemberRepository.findOne(id);
            if (projectMember != null) {
                projectMember.setStatus(PersistenceConstant.Status.DELETED);
                this.projectMemberRepository.save(projectMember);
            } else {
                throw new EntityNotFoundException("ProjectMember not found by given id.");
            }
        }
    }

    @Override
    public ProjectMemberDTO findByProjectIdAndUserId(Long projectId, Long userId) {
        if (projectId != null && userId != null) {
            ProjectMember member = this.projectMemberRepository.findOneByProjectIdAndUserId(projectId, userId);
            if (member != null) {
                return this.projectMemberMapper.projectMemberToProjectMemberDTO(member);
            }
        }
        return null;
    }

    @Override
    public List<ProjectMemberDTO> findByProjectIdAndStatus(Long projectId, String status) {
        if (projectId != null && status != null) {
            List<ProjectMember> projectMembers = this.projectMemberRepository.findByProjectIdAndStatus(projectId, status);
            if (projectMembers != null && !projectMembers.isEmpty()) {
                return this.projectMemberMapper.projectMembersToProjectMemberDTOs(projectMembers);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public ProjectMemberDTO findOneByProjectIdAndUserIdAndStatus(Long projectId, Long userId, String status) {
        if (projectId != null && userId != null && status != null) {
            ProjectMember projectMember = this.projectMemberRepository.findOneByProjectIdAndUserIdAndStatus(projectId, userId, status);
            if (projectMember != null) {
                return this.projectMemberMapper.projectMemberToProjectMemberDTO(projectMember);
            }
        }
        return null;
    }

}
