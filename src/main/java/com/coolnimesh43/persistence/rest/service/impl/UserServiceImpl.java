package com.coolnimesh43.persistence.rest.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolnimesh43.persistence.config.security.SecurityUtil;
import com.coolnimesh43.persistence.entity.User;
import com.coolnimesh43.persistence.exception.EntityNotFoundException;
import com.coolnimesh43.persistence.mapper.UserMapper;
import com.coolnimesh43.persistence.repository.UserRepository;
import com.coolnimesh43.persistence.rest.dto.UserDTO;
import com.coolnimesh43.persistence.rest.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @Override
    public UserDTO findOne(Long id) {
        if (id != null) {
            User user = this.userRepository.findOne(id);
            if (user != null) {
                return this.userMapper.userToUserDTO(user);
            }
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            if (StringUtils.isNotBlank(login)) {
                user = this.userRepository.findOneByLogin(login);
                if (user != null) {
                    return user;
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred while trying to find user by login : {}. Exception is: {}", login, e);
        }
        return user;
    }

    @Override
    public UserDTO findByEmail(String email) {
        UserDTO userDTO = null;
        try {
            if (StringUtils.isNotBlank(email)) {
                User user = this.userRepository.findByEmail(email);
                if (user != null) {
                    return this.userMapper.userToUserDTO(user);
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred while trying to find user by login : {}. Exception is: {}", email, e);
        }
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        if (userDTO != null) {
            User user = this.userMapper.userDTOToUser(userDTO);
            user = this.userRepository.save(user);
            return this.userMapper.userToUserDTO(user);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        User user = this.userRepository.findOne(id);
        if (user != null) {
            user.setEnabled(Boolean.FALSE);
            this.userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found with key " + id);
        }
    }

    public User getCurrentLoggedInUser() {
        org.springframework.security.core.userdetails.User securityUser = SecurityUtil.getCurrentUser();
        if (securityUser != null) {
            String login = securityUser.getUsername();
            User currentUser = this.findByLogin(login);
            return currentUser != null ? currentUser : null;
        }
        return null;
    }
}
