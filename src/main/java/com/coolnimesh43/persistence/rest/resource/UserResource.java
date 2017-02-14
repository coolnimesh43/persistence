package com.coolnimesh43.persistence.rest.resource;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolnimesh43.persistence.rest.dto.UserDTO;
import com.coolnimesh43.persistence.rest.service.ProjectMemberService;
import com.coolnimesh43.persistence.rest.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private UserService userService;

    @Inject
    private ProjectMemberService projectMemberService;

    @RequestMapping(value = "id/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get(@NotNull @PathVariable Long id) {
        try {
            UserDTO user = this.userService.findOne(id);
            if (user != null) {
                return ResponseEntity.ok().body(user);
            }
        } catch (Exception e) {
            log.error("Exception while getting user detail for id: {}. Exception is: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        try {
            UserDTO user = this.userService.save(userDTO);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            log.error("Exception while saving user. Exception is: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserDTO> update(@RequestBody @NotNull UserDTO userDTO) {
        try {
            if (userDTO.getId() == null) {
                return this.save(userDTO);
            }
            UserDTO user = this.userService.save(userDTO);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            log.error("Exception while updating user. Exception is: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
