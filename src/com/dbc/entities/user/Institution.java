package com.dbc.entities.user;

import com.dbc.entities.Request;

import java.util.Scanner;

public class Institution extends User {
    private String cnpj;

    public Institution() {}
    public Institution(String name, String email, String cnpj)  {
        super(name, email);
        this.setCnpj(cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public Boolean createUser(String name, String email, String cnpj) {
        for (User user : User.getUserDB()) {
            if (user instanceof Institution) {
                Institution institution = (Institution) user;
                if (institution.getCnpj().equalsIgnoreCase(cnpj)){
                    return false;
                }
            }
        }
        new Institution (name, email, cnpj);
        return true;
    }

    @Override
    public Boolean updateUser(Integer id) {
        Institution institution = userToInstitution(id);
        if (institution != null) {
            clear();
            Scanner scanner = new Scanner(System.in);
            String name, email, cnpj;

            System.out.printf("Trocar nome de usuario: ");
            name = scanner.nextLine();
            System.out.printf("Trocar email: ");
            email = scanner.nextLine();
            System.out.printf("Trocar cnpj: ");
            cnpj = scanner.nextLine();
            if (!cnpj.equals("")){
                for (User user :
                        User.getUserDB()) {
                    if (user instanceof Institution) {
                        Institution institution2 = (Institution) user;
                        if (institution2.getCnpj().equalsIgnoreCase(cnpj)){
                            return false;
                        }
                    }
                }
            }

            if (!name.equals("")){
                institution.setName(name);
            }
            if (!email.equals("")){
                institution.setEmail(email);
            }
            institution.setCnpj(cnpj);
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
                ", cnpj='" + getCnpj() + '\'' +
                '}';
    }

    public void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
