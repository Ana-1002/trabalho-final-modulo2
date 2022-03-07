package com.dbc.entities.user;

import com.dbc.entities.Request;
import com.dbc.interfaces.Crud;
import java.util.ArrayList;

public abstract class User implements Crud {
    private Integer id;
    private String name, email;
    private ArrayList<Request> myRequestsList = new ArrayList<>();

    private static ArrayList<User> userDB = new ArrayList<>();
    private static Integer count = 0;

    public User(){}
    public User(String name, String email) {
        this.setId(count++);
        this.setName(name);
        this.setEmail(email);
        userDB.add(this);
    }


    public ArrayList<Request> getMyRequestsList() {
        return myRequestsList;
    }

    public void setMyRequestsList(Request request) {
        this.myRequestsList.add(request);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Request createNewRequest () {
        return null;
    }

    public static ArrayList<User> getUserDB() {
        return userDB;
    }

    @Override
    public String toString() {
        if (getUserById(this.getId()) instanceof Person){
           return userToPerson(this.getId()).toString();

        } else if (getUserById(this.getId()) instanceof Institution) {
            return userToInstitution(this.getId()).toString();

        } else {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", myRequestsList=" + myRequestsList +
                    '}';
        }
    }

    public static void listPrint (){
        for (User user :
                getUserDB()) {
            System.out.println(user.getId() + " - " + user.getName());
        }
    }

    public static User getUserById (Integer id) {
        for (User user : getUserDB()) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public Institution userToInstitution (Integer id) {
        if (getUserById(id) instanceof Institution) {
            return (Institution) getUserById(id);
        }
        return null;
    }

    public Person userToPerson(Integer id) {
        if (getUserById(id) instanceof Institution) {
            return (Person) getUserById(id);
        }
        return null;
    }
}