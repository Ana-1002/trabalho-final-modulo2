package com.dbc.tests;

import com.dbc.entities.user.Person;
import com.dbc.entities.user.User;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class PersonTest {

        @Test
        public void deletePerson(){
            Person person = new Person();
            person.createUser("Name Person", "email@email.com", "CPF");
            person.createUser("Name Person2", "email2@email.com", "CPF2");

            User.listPrint();

            assertTrue(User.getUserById(0).deleteUser());

            User.listPrint();
        }
}
