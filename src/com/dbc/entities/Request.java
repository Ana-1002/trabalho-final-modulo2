package com.dbc.entities;


import com.dbc.entities.user.User;

public class Request {

    private Integer idRequest;
    private String title, description;
    private Double goal, reachedValue;
    private Category category;
    private BankAccount account;
    private User user;

    public Integer getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGoal() {
        return goal;
    }

    public void setGoal(Double goal) {
        this.goal = goal;
    }

    public Double getReachedValue() {
        return reachedValue;
    }

    public void setReachedValue(Double reachedValue) {
        this.reachedValue = 0.0;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Request{" +
                "idRequest=" + idRequest +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", goal=" + goal +
                ", reachedValue=" + reachedValue +
                ", category=" + category +
                ", account=" + account +
                ", user=" + user +
                '}';
    }
}