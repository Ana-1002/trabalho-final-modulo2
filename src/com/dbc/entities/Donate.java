package com.dbc.entities;
import java.util.Scanner;

public class Donate {
    private Integer id_donate;
    private String donator_name;
    private String donator_email;
    private Double donate_value;
    private String description;
    private Request request;
    public Donate(){

    }

//    public Donate(String nameDonator, String cpfDonator, String emailDonator, Double donateValue, String descripition, String accoutInformation){
//        this.setNameDonator(nameDonator);
//        this.setCpfDonator(cpfDonator);
//        this.setEmailDonator(emailDonator);
//        this.setDonateValue(donateValue);
//        this.setDescription(descripition);
//    }
//
//    public Boolean createDonate() {
//
//         Scanner scanner = new Scanner(System.in);
//         Request.getAllRequests();
//         System.out.print("Informe o ID da vakinha para qual voce deseja doar -> ");
//         Integer id= scanner.nextInt();
//         scanner.nextLine();
//         if (new Request().getRequestById(id) == null){
//             return false;
//         }
//         Double valor;
//         System.out.println();
//        System.out.println("(Digitar 'válido' para validar pagamento)");
//         String payStatus = scanner.nextLine();
//         clear();
//
//        if (BankAccount.simulatePayment(payStatus)) {
//
//            System.out.print("Informe o nome: ");
//            this.setNameDonator(scanner.nextLine());
//            System.out.print("Informe o CPF: ");
//            this.setCpfDonator(scanner.nextLine());
//            System.out.print("Informe o email: ");
//            this.setEmailDonator(scanner.nextLine());
//            System.out.print("Informe o valor: ");
//            valor=scanner.nextDouble();
//            if(valor>0){
//            this.setDonateValue(valor);
//            } else {return false;}
//            scanner.nextLine();
//            System.out.println("Você gostaria de deixar uma mensagem? (Digite 'sim' ou 'nao')");
//            String description = scanner.nextLine();
//
//            if (description.equalsIgnoreCase("sim")) {
//                System.out.print("Digite sua mensagem: ");
//                this.setDescription(scanner.nextLine());
//            } else {
//                this.setDescription("");
//            }
//            Request request = new Request().getRequestById(id);
//            request.addNewDonate(this);
//            return true;
//        }
//        return false;
//    }


    public Integer getId_donate() {
        return id_donate;
    }

    public void setId_donate(Integer id_donate) {
        this.id_donate = id_donate;
    }

    public String getDonator_name() {
        return donator_name;
    }

    public void setDonator_name(String donator_name) {
        this.donator_name = donator_name;
    }

    public String getDonator_email() {
        return donator_email;
    }

    public void setDonator_email(String donator_email) {
        this.donator_email = donator_email;
    }

    public Double getDonate_value() {
        return donate_value;
    }

    public void setDonate_value(Double donate_value) {
        this.donate_value = donate_value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}