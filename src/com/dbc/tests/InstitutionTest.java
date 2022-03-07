package com.dbc.tests;

import com.dbc.entities.user.Institution;
import com.dbc.entities.user.Person;
import com.dbc.entities.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstitutionTest {

    @Test
    public void testCreateRemoveInstitution () {
        Institution institution = new Institution();
        institution.createUser("Name Institution", "email@email.com", "CNPJ");
        institution.createUser("Name Institution2", "email2@email.com", "CNPJ2");
        Person person = new Person();
        person.createUser("Name Person", "email@email.com", "CPF");

        User.listPrint();

        assertTrue(User.getUserById(0).deleteUser());

        User.listPrint();
    }
}
