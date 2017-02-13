package com.coolnimesh43.persistence.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.coolnimesh43.persistence.enums.ProjectPriority;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "priority")
    @Enumerated(EnumType.ORDINAL)
    private ProjectPriority priority;

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(name = "description")
    private String description;

    @Size(max = 300)
    @Column(name = "project_image", length = 300)
    private String projectImage;

    @Size(max = 50)
    @Column(name = "project_asset_folder_name", length = 50)
    private String projectAssetFolderName;

    @ManyToOne
    @JoinColumn(name = "parent_project_id", nullable = true)
    @JsonIgnore
    private Project parentProject;

    public Project() {
        super();
    }

    private Project(Builder builder) {
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.priority = builder.priority;
        this.description = builder.description;
        this.projectImage = builder.projectImage;
        this.projectAssetFolderName = builder.projectAssetFolderName;
        this.parentProject = builder.parentProject;
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

    public Project getParentProject() {
        return parentProject;
    }

    public void setParentProject(Project parentProject) {
        this.parentProject = parentProject;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority
                + ", description=" + description + ", projectImage=" + projectImage + ", projectAssetFolderName=" + projectAssetFolderName
                + ", parentProject=" + parentProject + "]";
    }

    public static class Builder {
        private String name;
        private ZonedDateTime startDate;
        private ZonedDateTime endDate;
        private ProjectPriority priority;
        private String description;
        private String projectImage;
        private String projectAssetFolderName;
        private Project parentProject;

        public Builder(String name) {
            this.name = name;
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

        public Builder parentProject(Project parentProject) {
            this.parentProject = parentProject;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

}
