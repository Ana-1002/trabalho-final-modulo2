package com.dbc.entities;


import com.dbc.entities.user.User;
import com.dbc.enums.Categories;

import java.util.ArrayList;
import java.util.Scanner;

public class Request {

    private Integer id;
    private User owner;
    private BankAccount bankAccount;
    private Categories category;
    private String title, description;
    private Double goal;
    private Double reachedValue;
    private ArrayList<Donate> donatesList;

    public static ArrayList<Request> requestsDB = new ArrayList<>();
    public static ArrayList<Request> closedRequestList = new ArrayList<>();

    public static Integer idCount = 0;

    public Request() {}

    public Request(User owner, BankAccount bankAccount, Categories category, String title, String description, Double goal) {
        this.setId(idCount++);
        this.setOwner(owner);
        this.setBankAccount(bankAccount);
        this.setCategory(category);
        this.setTitle(title);
        this.setDescription(description);
        this.goal = goal;
        this.reachedValue = 0.0;
        this.donatesList = new ArrayList<>();

        requestsDB.add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer finalId = id;
        boolean idExists = requestsDB.stream()
                .map(Request::getId)
                .anyMatch(r -> r.equals(finalId));

        if (!idExists) {
            this.id = id;
        } else {
            this.id = ++id;
        }
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        if (owner != null) {
            this.owner = owner;
        }
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        if (bankAccount != null) {
            this.bankAccount = bankAccount;
        }
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGoal() {
        return goal;
    }

    public Double getReachedValue() {
        return reachedValue;
    }

    public void setReachedValue(Double value) {
        this.reachedValue += value;
    }

    public ArrayList<Donate> getDonatesList() {
        return donatesList;
    }

    public boolean setNewDonate(Donate donate) {
        Double value = donate.getDonateValue();

        if (this.checkIfGoalHasReached()) {
            if (this.closeOrder()) {
                return false;
            }
        }

        this.setReachedValue(value);
        this.donatesList.add(donate);

        if (this.checkIfGoalHasReached()) {
            if (this.closeOrder()) {
                return true;
            }
        }

        return false;
    }

    public static void getAllRequests() {
       requestsDB.stream().forEach(System.out::println);
    }

    public static void getRequestsByCategory(Categories category) {
        requestsDB.stream()
                .filter(request -> request.getCategory().equals(category))
                .forEach(System.out::println);
    }

    public boolean checkIfGoalHasReached() {
        if (this.getReachedValue() >= this.getGoal()) {
            return true;
        }
        return false;
    }

    public boolean closeOrder() {
        Request clone = this;
        if (requestsDB.remove(this)) {
            closedRequestList.add(clone);
            return true;
        }
        return false;
    }

    public Request getRequestById(Integer id) {
        Request request = requestsDB.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
        return request;
    }

    public boolean addNewDonate(Donate donate) {
        if (donate != null) {
            if (this.setNewDonate(donate)) {
                return true;
            }
        }
        return false;
    }

    public void editRequest() {
        Scanner scanner = new Scanner(System.in);
        String title, description;

        System.out.print("Digite o novo titulo: ");
        title = scanner.nextLine();
        System.out.print("Digite a nova descrição: ");
        description = scanner.nextLine();

        if (title != "" && title != " ") {
            this.setTitle(title);
        }

        if (description != "" && description != " ") {
            this.setDescription(description);
        }
    }

    @Override
    public String toString() {
        return this.getId() + " - " +
                this.getTitle() + "\n" +
                "Categoria: " + this.getCategory().getDescription() + "\n"
                + "Valor: " +
                this.getReachedValue() + "/" +
                this.getGoal() + "\nDescrição: " +
                this.getDescription() + "\nUsuário: " +
                this.getOwner().getName() + "\n";
    }
}