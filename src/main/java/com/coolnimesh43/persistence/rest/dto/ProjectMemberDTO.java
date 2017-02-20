package com.coolnimesh43.persistence.rest.dto;

public class ProjectMemberDTO {

    private Long id;
    private String status;
    private Long projectId;
    private Long userId;
    private ProjectDTO projectDTO;

    public ProjectMemberDTO() {
        super();
    }

    private ProjectMemberDTO(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.projectId = builder.projectId;
        this.userId = builder.userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    @Override
    public String toString() {
        return "ProjectMemberDTO [id=" + id + ", status=" + status + ", projectId=" + projectId + ", userId=" + userId + "]";
    }

    public static class Builder {
        private Long id;
        private String status;
        private Long projectId;
        private Long userId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ProjectMemberDTO build() {
            return new ProjectMemberDTO(this);
        }
    }

}
