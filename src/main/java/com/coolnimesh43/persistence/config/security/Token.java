package com.coolnimesh43.persistence.config.security;

/**
 * The security token.
 */
public class Token {

    String token;
    long expires;

    public Token(String token, long expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public long getExpires() {
        return expires;
    }

    @Override
    public String toString() {
        return "Token [token=" + token + ", expires=" + expires + "]";
    }

}
