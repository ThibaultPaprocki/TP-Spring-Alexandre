package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepositoryDTO {
    private String name;
    private String login;
    private Integer open_issues;
    private Integer forks;

    public GitRepositoryDTO() {}

    public GitRepositoryDTO(String name, String login, Integer open_issues, Integer forks) {
        this.name=name;
        this.login=login;
        this.open_issues=open_issues;
        this.forks=forks;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String owner) {
        this.login = owner;
    }

    public Integer getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(Integer open_issues) {
        this.open_issues = open_issues;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }
}