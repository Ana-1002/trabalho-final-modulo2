package com.dbc.tests;

import com.dbc.entities.BankAccount;
import com.dbc.entities.Donate;
import com.dbc.entities.Request;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class DonateTest {
    @Test
    public void deveTestarCreateDonate() {
        Donate donate=new Donate();
        Integer id= 2;
        boolean var = false;

        String payStatus = "válido";

        if (BankAccount.simulatePayment(payStatus)) {

            donate.setNameDonator("ana");
            donate.setCpfDonator("6533");
            donate.setEmailDonator("ana@gmail");
            donate.setDonateValue(30.0);
            String description = "SIM";

            var = true;
        }
        assertTrue(var);
    }

    @Test
    public void deveTestarDescription(){
        Donate donate=new Donate();
        String description= "sim";

        if (description.equalsIgnoreCase("sim")) {
            donate.setDescription("BOA SORTE");
        } else {
            donate.setDescription(null);
        }
        assertEquals(donate.getDescription(), "BOA SORTE");
    }
}
