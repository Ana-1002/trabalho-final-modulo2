package com.dbc.entities;
import java.util.Scanner;

public class Donate {
    private String nameDonator;
    private String cpfDonator;
    private String emailDonator;
    private Double donateValue;
    private String description;

    public Donate(){

    }

    public Donate(String nameDonator, String cpfDonator, String emailDonator, Double donateValue, String descripition, String accoutInformation){
        this.setNameDonator(nameDonator);
        this.setCpfDonator(cpfDonator);
        this.setEmailDonator(emailDonator);
        this.setDonateValue(donateValue);
        this.setDescription(descripition);
    }

    public Boolean createDonate() {

         Scanner scanner = new Scanner(System.in);
         Request.getAllRequests();
         System.out.print("Informe o ID da vakinha para qual voce deseja doar -> ");
         Integer id= scanner.nextInt();
         scanner.nextLine();
         if (new Request().getRequestById(id) == null){
             return false;
         }
         Double valor;
         System.out.println();
        System.out.println("(Digitar 'válido' para validar pagamento)");
         String payStatus = scanner.nextLine();
         clear();

        if (BankAccount.simulatePayment(payStatus)) {

            System.out.print("Informe o nome: ");
            this.setNameDonator(scanner.nextLine());
            System.out.print("Informe o CPF: ");
            this.setCpfDonator(scanner.nextLine());
            System.out.print("Informe o email: ");
            this.setEmailDonator(scanner.nextLine());
            System.out.print("Informe o valor: ");
            valor=scanner.nextDouble();
            if(valor>0){
            this.setDonateValue(valor);
            } else {return false;}
            scanner.nextLine();
            System.out.println("Você gostaria de deixar uma mensagem? (Digite 'sim' ou 'nao')");
            String description = scanner.nextLine();

            if (description.equalsIgnoreCase("sim")) {
                System.out.print("Digite sua mensagem: ");
                this.setDescription(scanner.nextLine());
            } else {
                this.setDescription("");
            }
            Request request = new Request().getRequestById(id);
            request.addNewDonate(this);
            return true;
        }
        return false;
    }

    public Boolean donateRequest (Integer integer){
        return false;
    }

    public String getNameDonator() {
        return nameDonator;
    }

    public void setNameDonator(String nameDonator) {
        this.nameDonator = nameDonator;
    }

    public String getCpfDonator() {
        return cpfDonator;
    }

    public void setCpfDonator(String cpfDonator) {
        this.cpfDonator = cpfDonator;
    }

    public String getEmailDonator() {
        return emailDonator;
    }

    public void setEmailDonator(String emailDonator) {
        this.emailDonator = emailDonator;
    }

    public Double getDonateValue() {
        return donateValue;
    }

    public void setDonateValue(Double donateValue) {
        this.donateValue = donateValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripition) {
        this.description = descripition;
    }

    public void clear () {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}