package com.dbc.entities.user;

import com.dbc.entities.Request;

import java.util.Scanner;

public class Person extends User {
    private String cpf;

    public Person(){}
    public Person(String name, String email, String cpf) {
        super(name, email);
        this.setCpf(cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public Boolean createUser(String name, String email, String cpf) {
        for (User user :
                User.getUserDB()) {
            if (user instanceof Person) {
                Person person = (Person) user;
                if (person.getCpf().equalsIgnoreCase(cpf)){
                    return false;
                }
            }
        }
        new Person (name, email, cpf);
        return true;
    }

    @Override
    public Boolean updateUser(Integer id) {
        Person person = userToPerson(id);
        if (person != null) {
            clear();
            Scanner scanner = new Scanner(System.in);
            String name, email, cpf;

            System.out.printf("Trocar nome de usuario: ");
            name = scanner.nextLine();
            System.out.printf("Trocar email: ");
            email = scanner.nextLine();
            System.out.printf("Trocar cpf: ");
            cpf = scanner.nextLine();
            if (!cpf.equals("")){
                for (User user :
                        User.getUserDB()) {
                    if (user instanceof Institution) {
                        Institution institution2 = (Institution) user;
                        if (institution2.getCnpj().equalsIgnoreCase(cpf)){
                            return false;
                        }
                    }
                }
            }

            if (!name.equals("")){
                person.setName(name);
            }
            if (!email.equals("")){
                person.setEmail(email);
            }
            person.setCpf(cpf);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteUser() {
        if (this != null) {
            Request.requestsDB.removeIf(request -> request.getOwner().equals(this));
            User.getUserDB().remove(this);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", myRequestsList=" + getMyRequestsList() +
                ", cpf='" + getCpf() + '\'' +
                '}';
    }

    public void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
