package com.dbc.entities;

public class BankAccount {

    private String number, agency;

    public BankAccount(){
    }

    public BankAccount(String number, String agency){
        this.number=number;
        this.agency=agency;
    }

    public static Boolean simulatePayment (String str) {
        if (str.equalsIgnoreCase("válido")){
            return true;
        }
        return false;
    }

    public String simulateInvoice (Donate donate){
        String invoice;
        invoice = "Nota fiscal:\n"+ "Nome: \t"+ donate.getNameDonator()+"\n"+" Valor: \t"+
               donate.getDonateValue()+"\n"+"Cpf: \t"+ donate.getCpfDonator()+ "\n Obrigado pela colaboração!";

        return invoice;

    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
