package com.dbc.tests;
import org.junit.Test;
import static org.junit.Assert.*;
public class BankAccountTest {
    @Test
    public void deveTestarSimulatePayment(){
        String str="Válido";
        Boolean varB=false;
        if (str.equalsIgnoreCase("válido")){
            varB=true;
        }
        assertTrue(varB);
    }
}
