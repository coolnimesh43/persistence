package com.coolnimesh43.persistence.rest.dto;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import com.coolnimesh43.persistence.enums.ProjectPriority;

public class ProjectDTO extends AbstractAuditingDTO {

    private Long id;

    @NotNull
    private String name;

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private ProjectPriority priority;
    private String description;
    private String projectImage;
    private String projectAssetFolderName;
    private Long parentProjectId;

    @NotNull
    private String status;

    public ProjectDTO() {
        super();
    }

    private ProjectDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.priority = builder.priority;
        this.description = builder.description;
        this.projectImage = builder.projectImage;
        this.projectAssetFolderName = builder.projectAssetFolderName;
        this.parentProjectId = builder.parentProjectId;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public ProjectPriority getPriority() {
        return priority;
    }

    public void setPriority(ProjectPriority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }

    public String getProjectAssetFolderName() {
        return projectAssetFolderName;
    }

    public void setProjectAssetFolderName(String projectAssetFolderName) {
        this.projectAssetFolderName = projectAssetFolderName;
    }

    public Long getParentProjectId() {
        return parentProjectId;
    }

    public void setParentProjectId(Long parentProjectId) {
        this.parentProjectId = parentProjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProjectDTO [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority
                + ", description=" + description + ", projectImage=" + projectImage + ", projectAssetFolderName=" + projectAssetFolderName
                + ", parentProjectId=" + parentProjectId + "]";
    }

    public static class Builder {
        private Long id;
        private String name;
        private ZonedDateTime startDate;
        private ZonedDateTime endDate;
        private ProjectPriority priority;
        private String description;
        private String projectImage;
        private String projectAssetFolderName;
        private Long parentProjectId;
        private String status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder startDate(ZonedDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(ZonedDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder priority(ProjectPriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder projectImage(String projectImage) {
            this.projectImage = projectImage;
            return this;
        }

        public Builder projectAssetFolderName(String projectAssetFolderName) {
            this.projectAssetFolderName = projectAssetFolderName;
            return this;
        }

        public Builder parentProjectId(Long parentProjectId) {
            this.parentProjectId = parentProjectId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public ProjectDTO build() {
            return new ProjectDTO(this);
        }
    }

}
