package com.coolnimesh43.persistence.rest.service;

import com.coolnimesh43.persistence.entity.User;
import com.coolnimesh43.persistence.exception.UserNotFoundException;
import com.coolnimesh43.persistence.rest.dto.UserDTO;

public interface UserService {

    /**
     * Find {@link User} by primary key, id.
     * 
     * @author coolnimesh43
     * @param id
     *            {@link Long} {@link User#getId()}
     * @return {@link User} The entity is returned if it exists, else null.
     */
    UserDTO findById(Long id);

    /**
     * Find {@link User} by login {@link User#getLogin()}.
     * 
     * @author coolnimesh43
     * @param login
     *            {@link String} The login of the user {@link User#getLogin()}
     * @return {@link User} The entity is returns if it exists, else null.
     */
    UserDTO findByLogin(String login);

    /**
     * Find {@link User} by user's email, {@link User#getEmail()}.
     * 
     * @author coolnimesh43
     * @param email
     *            {@link String} The email of the user, {@link User#getEmail()}
     * @return {@link User} The entity is returned if it exists, else null.
     */
    UserDTO findByEmail(String email);

    /**
     * Save the given user.
     * 
     * @author coolnimesh43
     * @param user
     *            {@link UserDTO} The user details to be saved.
     * @return {@link User} The saved entity.
     */
    UserDTO save(UserDTO user);

    /**
     * Delete the user which has the given primary key.
     * 
     * @author coolnimesh43
     * @param id
     *            {@link Long} The primary key
     */
    void delete(Long id) throws UserNotFoundException;
}
