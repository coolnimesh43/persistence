package com.coolnimesh43.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.coolnimesh43.persistence.constant.PersistenceConstant;

@Entity
@Table(name = "project_member")
public class ProjectMember extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "status", length = 10, nullable = false)
    private String status = PersistenceConstant.Status.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ProjectMember() {
        super();
    }

    public ProjectMember(String status, Project project, User user) {
        super();
        this.status = status;
        this.project = project;
        this.user = user;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ProjectMember [id=" + id + ", status=" + status + ", project=" + project + ", user=" + user + "]";
    }

}
