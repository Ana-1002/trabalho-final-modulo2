package com.dbc.tests;

import com.dbc.entities.user.Person;
import com.dbc.entities.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testUsuario (){
        //setup
        Person person = new Person("nome", "email", "cpf");
        User.listPrint();

        //action
        User user = User.getUserById(0);
        if (user instanceof Person){
            Person person1 = (Person) user;
            System.out.println(person1.getCpf());
        }
    }
}
