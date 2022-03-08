package com.dbc.entities.user;

public class User {
    private Integer idUser;
    private String name;
    private String email;
    private String password;
    private Integer type = 1;
    private String document;

    public User(){}
    public User(String name, String email, String password, Integer type, String document) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setType(type);
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Boolean isPerson () {
        if (this.getType() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isInstitution () {
        if (this.getType() == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "\n idUser =\t" + idUser +
                "\n name =\t\t'" + name + '\'' +
                "\n email =\t'" + email + '\'' +
                "\n password =\t'" + password + '\'' +
                "\n type =\t\t" + (this.isPerson() ? "Person (" : "Institution (") + type + ')' +
                "\n document =\t'" + document + '\'' +
                "\n}";
    }
}