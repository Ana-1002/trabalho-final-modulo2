package com.dbc.entities;

public class BankAccount {

    private Integer id_bank_account;
    private String account_number, agency;

    public BankAccount(){
    }


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

    @Override
    public String toString() {
        return "BankAccount{" +
                "id_bank_account=" + id_bank_account +
                ", account_number='" + account_number + '\'' +
                ", agency='" + agency + '\'' +
                '}';
    }
}
