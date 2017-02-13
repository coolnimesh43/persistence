package com.coolnimesh43.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_table")
public class User extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    @NotNull
    private String email;

    @JsonIgnore
    @NotNull
    @Column(name = "salt", nullable = false)
    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    private String salt;

    @JsonIgnore
    @NotNull
    @Column(name = "password", nullable = false)
    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    private String password;

    @NotNull
    @Column(name = "login", nullable = false, updatable = false, unique = true)
    private String login;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    public User() {
        super();
    }

    public User(String firstName, String email, String login) {
        super();
        this.firstName = firstName;
        this.email = email;
        this.login = login;
    }

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.salt = builder.salt;
        this.password = builder.password;
        this.login = builder.login;
        this.enabled = builder.enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", login=" + login + "]";
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String salt;
        private String password;
        private String login;
        private Boolean enabled;

        public Builder(String firstName, String email, String login) {
            super();
            this.firstName = firstName;
            this.email = email;
            this.login = login;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
