package com.coolnimesh43.persistence.rest.dto;

import com.coolnimesh43.persistence.config.security.Token;

public class SignInResponse extends GenericResponse {

    private Token token;
    private UserDTO user;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SignInResponse [token=" + token + ", user=" + user + "]";
    }

}
