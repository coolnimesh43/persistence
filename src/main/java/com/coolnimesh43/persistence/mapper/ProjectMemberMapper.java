package com.coolnimesh43.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.coolnimesh43.persistence.entity.ProjectMember;
import com.coolnimesh43.persistence.rest.dto.ProjectMemberDTO;

@Mapper(componentModel = "spring", uses = { UserMapper.class, ProjectMapper.class })
public interface ProjectMemberMapper {

    @Mappings(value = { @Mapping(target = "projectId", source = "project.id"), @Mapping(target = "userId", source = "user.id") })
    ProjectMemberDTO projectMemberToProjectMemberDTO(ProjectMember projectMember);

    List<ProjectMemberDTO> projectMembersToProjectMemberDTOs(List<ProjectMember> projectMembers);

    @Mappings(value = { @Mapping(target = "user", source = "userId"), @Mapping(target = "project", source = "projectId") })
    ProjectMember projectMemberDTOToProjectMember(ProjectMemberDTO projectMemberDTO);

    List<ProjectMember> projectMemberDTOsToProjectMembers(List<ProjectMember> projectMembers);
}
