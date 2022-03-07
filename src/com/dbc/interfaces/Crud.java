package com.dbc.interfaces;

public interface Crud {
    Boolean createUser (String name, String email, String key);
    Boolean updateUser (Integer id);
    Boolean deleteUser ();
}