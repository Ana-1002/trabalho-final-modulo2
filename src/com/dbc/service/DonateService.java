package com.dbc.service;
import com.dbc.entities.BankAccount;
import com.dbc.entities.Donate;
import com.dbc.entities.Request;
import com.dbc.repository.BankAccountRepository;
import com.dbc.repository.DonateRepository;
import com.dbc.repository.RequestRepository;

import java.sql.SQLException;
import java.util.List;

public class DonateService {

    private DonateRepository donateRepository;
    public DonateService(){
        donateRepository= new DonateRepository();
    }

    public void add(Donate donate){
        try {
            if (testDonate(donate, donate.getRequest().getIdRequest())){
                throw new Exception("Não foi possível realizar a doação");
            }
            donateRepository.add(donate);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public boolean testDonate (Donate donate, Integer id) throws SQLException {
        Request request = new RequestRepository().getRequestById(id);
            if (request.getReachedValue()>= request.getGoal()){
                return true;
            }
        return false;
    }

    public void remove(Integer id){
        try {
            boolean removed = donateRepository.remove(id);
            System.out.println("donate removida? " + removed + "| com id=" + id);

        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    public void update(Integer id, Donate donate){
        try {
            boolean updated = donateRepository.update(id, donate);
            System.out.println("Donate editada? " + updated + "| com id=" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void list() {
        try {
            List<Donate> list = donateRepository.list();
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
