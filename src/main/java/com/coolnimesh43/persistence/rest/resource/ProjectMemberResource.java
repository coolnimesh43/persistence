package com.coolnimesh43.persistence.rest.resource;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolnimesh43.persistence.constant.PersistenceConstant;
import com.coolnimesh43.persistence.rest.dto.ProjectMemberDTO;
import com.coolnimesh43.persistence.rest.service.ProjectMemberService;

@RestController
@RequestMapping(value = "/api/project-member")
public class ProjectMemberResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private ProjectMemberService projectMemberService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProjectMemberDTO> create(@RequestBody @Valid ProjectMemberDTO projectMemberDTO) {
        try {
            projectMemberDTO.setStatus(PersistenceConstant.Status.ACTIVE);
            projectMemberDTO = this.projectMemberService.save(projectMemberDTO);
            if (projectMemberDTO != null) {
                return ResponseEntity.ok(projectMemberDTO);
            }
        } catch (Exception e) {
            log.error("Exception while creating project member. Exception is: {}", e);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
