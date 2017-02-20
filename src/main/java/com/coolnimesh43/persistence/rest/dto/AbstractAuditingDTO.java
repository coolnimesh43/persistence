package com.coolnimesh43.persistence.rest.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractAuditingDTO implements Serializable {

    @ReadOnlyProperty
    private String createdBy;

    @ReadOnlyProperty
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @JsonIgnore
    private String lastModifiedBy;

    @JsonIgnore
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "AbstractAuditingDTO [createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
                + ", lastModifiedDate=" + lastModifiedDate + "]";
    }

}
