package com.dbc.service;

import com.dbc.entities.Category;
import com.dbc.entities.user.User;
import com.dbc.repository.CategoryRepository;
import com.dbc.repository.UserRepository;

import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.Locale;

public class UserService {

    //TODO - remover static
    private static final UserRepository USER_REPOSITORY = new UserRepository();

//    public static void main(String[] args) throws Exception {
//        USER_REPOSITORY.list().forEach(System.out::println);
//        User u = new User("nome", "email", "senha", 1, "23456789");
//        new UserService().add(u);
//        USER_REPOSITORY.list().forEach(System.out::println);
//    }

    public Boolean add (User user) throws Exception {
        try{
            if (this.userAlreadyExists(user)) {
                throw new Exception("Usuario invalido");
            }

            USER_REPOSITORY.add(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void remove(Integer id) {
        try {
            boolean isRemoved = USER_REPOSITORY.remove(id);
            System.out.println("removido? " + isRemoved + "| com id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, User user) {
        try {
            boolean isUpdated = USER_REPOSITORY.update(id, user);
            System.out.println("editado? " + isUpdated + "| com id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void list() {
        try {
            USER_REPOSITORY.list().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userAlreadyExists(User user) throws SQLException {
        for (User u : new UserRepository().list()) {
            if (u.getDocument().toLowerCase().equalsIgnoreCase(user.getDocument().toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
}
