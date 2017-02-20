package com.coolnimesh43.persistence.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolnimesh43.persistence.entity.User;
import com.coolnimesh43.persistence.rest.dto.ProjectDTO;
import com.coolnimesh43.persistence.rest.dto.ProjectMemberDTO;
import com.coolnimesh43.persistence.rest.service.ProjectMemberService;
import com.coolnimesh43.persistence.rest.service.ProjectService;
import com.coolnimesh43.persistence.rest.service.UserService;

@RestController
@RequestMapping("/api/project")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Inject
    private UserService userService;

    @Inject
    private ProjectService projectService;

    @Inject
    private ProjectMemberService projectMemberService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDTO>> getByUser() {
        User user = this.userService.getCurrentLoggedInUser();
        if (user != null) {
            List<ProjectMemberDTO> projectMembers = this.projectMemberService.findByUserId(user.getId());
            return ResponseEntity.ok(projectMembers.parallelStream().map(pm -> pm.getProjectDTO()).collect(Collectors.toList()));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProjectDTO> create(@RequestBody @Valid ProjectDTO projectDTO) {
        try {
            ProjectDTO project = this.projectService.save(projectDTO);
            if (project != null) {
                return ResponseEntity.ok(project);
            }
        } catch (Exception e) {
            log.error("Exception while creating project. Exception is: {}", e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(value = "/id/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDTO> get(@PathVariable Long projectId) {
        try {
            ProjectDTO project = this.projectService.findOne(projectId);
            if (project != null) {
                return ResponseEntity.ok(project);
            }
        } catch (Exception e) {
            log.error("Exception while getting project with id: {}. Exception is: {}", projectId, e);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
