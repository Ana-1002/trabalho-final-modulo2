package com.dbc.entities;

public class BankAccount {

    private Integer id_bank_account;
    private String account_number, agency;

    public BankAccount(){
    }

//    public BankAccount(String number, String agency){
//        this.number=number;
//        this.agency=agency;
//    }
//
//    public static Boolean simulatePayment (String str) {
//        if (str.equalsIgnoreCase("válido")){
//            return true;
//        }
//        return false;
//    }
//
//    public String simulateInvoice (Donate donate){
//        String invoice;
//        invoice = "Nota fiscal:\n"+ "Nome: \t"+ donate.getNameDonator()+"\n"+" Valor: \t"+
//               donate.getDonateValue()+"\n"+"Cpf: \t"+ donate.getCpfDonator()+ "\n Obrigado pela colaboração!";
//
//        return invoice;
//
//    }
//

    public Integer getId_bank_account() {
        return id_bank_account;
    }

    public void setId_bank_account(Integer id_bank_account) {
        this.id_bank_account = id_bank_account;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
