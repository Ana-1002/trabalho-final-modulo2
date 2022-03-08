package com.dbc.entities.user;

public abstract class User {
    private Integer idUser;
    private String name;
    private String email;
    private String password;
    private Boolean isPerson = true;
    private String document;

    public User(){}
    public User(String name, String email, String password, Boolean isPerson, String document) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setIsPerson(isPerson);
        this.setDocument(document);
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(Boolean person) {
        isPerson = person;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}