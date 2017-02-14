package com.coolnimesh43.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coolnimesh43.persistence.entity.ProjectMember;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    ProjectMember findOneByProjectIdAndUserId(Long projectId, Long userId);

    ProjectMember findOneByProjectIdAndUserIdAndStatus(Long projectId, Long userId, String status);

    List<ProjectMember> findByProjectIdAndStatus(Long projectId, String status);
}
