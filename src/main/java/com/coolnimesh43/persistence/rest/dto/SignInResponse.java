package com.coolnimesh43.persistence.rest.dto;

import com.coolnimesh43.persistence.config.security.Token;

public class SignInResponse extends GenericResponse {

    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SignInResponse [token=" + token + "]";
    }

}
