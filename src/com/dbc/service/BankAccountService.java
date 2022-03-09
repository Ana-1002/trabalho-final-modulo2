package com.dbc.service;

import com.dbc.entities.BankAccount;
import com.dbc.repository.BankAccountRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountService {

    private BankAccountRepository bank_account_repository;
    public BankAccountService(){
        bank_account_repository= new BankAccountRepository();
    }

    public void add(BankAccount bankAccount){
        try {
            if (testIfExist(bankAccount)){
                throw new Exception("Conta já Existente!");
            }
            bank_account_repository.add(bankAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public boolean testIfExist(BankAccount bankAccount) throws SQLException {
        for (BankAccount bankAccountList : new BankAccountRepository().list()) {
            if (bankAccountList.getAccount_number().equals(bankAccount.getAccount_number())){
                return true;
            }
        }
        return false;
    }

    public void remove(Integer id){
        try {
            boolean removed = bank_account_repository.remove(id);
            System.out.println("Conta removida? " + removed + "| com id=" + id);

        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    public void update(Integer id, BankAccount bankAccount){
        try {
            boolean updated = bank_account_repository.update(id, bankAccount);
            System.out.println("Conta editada? " + updated + "| com id=" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void list() {
        try {
            List<BankAccount> list = bank_account_repository.list();
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
