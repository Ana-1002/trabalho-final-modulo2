package com.dbc.application;

import com.dbc.entities.BankAccount;
import com.dbc.entities.Donate;
import com.dbc.entities.Request;
import com.dbc.entities.user.Institution;
import com.dbc.entities.user.Person;
import com.dbc.entities.user.User;
import com.dbc.enums.Categories;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        start();

        Scanner scanner = new Scanner(System.in);
        String str;

        do {
            clear();
            System.out.println("--------------VAKINHA--------------");
            System.out.println("1 - Cria usuario");
            System.out.println("2 - Lista usuario(s)");
            System.out.println("3 - Edita usuario");
            System.out.println("4 - Remove usuario");
            System.out.println("5 - Cria vakinha");
            System.out.println("6 - Lista vakinha(s)");
            System.out.println("7 - Lista vakinha(s) por categoria");
            System.out.println("8 - Doar valor");
            System.out.println("0 - Sair");
            System.out.println();
            System.out.print(" -> ");
            str = scanner.nextLine();

            switch (str){
                case "1":
                    clear();
                    userForm();
                    break;
                case "2":
                    clear();
                    User.listPrint();
                    pause();
                    break;
                case "3":
                    clear();
                    User.listPrint();
                    System.out.printf("");
                    String idUpdate = scanner.nextLine();
                    if (User.getUserById(Integer.parseInt(idUpdate)) != null){
                        if (User.getUserById(Integer.parseInt(idUpdate)) instanceof Person){
                            if (new Person().updateUser(Integer.parseInt(idUpdate))){
                                clear();
                                System.out.println("Usuario atualizado!");
                                pause();
                            } else {
                                clear();
                                System.out.println("Acao invalida");
                                pause();
                            }
                        } else {
                            if (new Institution().updateUser(Integer.parseInt(idUpdate))){
                                clear();
                                System.out.println("Usuario atualizado!");
                                pause();
                            } else {
                                clear();
                                System.out.println("Acao invalida");
                                pause();
                            }
                        }
                    } else {
                        clear();
                        System.out.println("Usuario nao encontrado!");
                    }

                    break;
                case "4":
                    clear();
                    User.listPrint();
                    System.out.println();
                    System.out.printf("Id do Usuario que deseja remover: ");
                    String idRemove = scanner.nextLine();

                    if (User.getUserById(Integer.parseInt(idRemove)) != null && User.getUserById(Integer.parseInt(idRemove)).deleteUser()) {
                        clear();
                        System.out.println("Usuario removido!");
                        pause();
                    } else {
                        clear();
                        System.out.println("Usuario nao encontrado!");
                        pause();
                    }
                    break;
                case "5":
                    clear();
                    requestForm();
                    break;
                case "6":
                    clear();
                    Request.getAllRequests();
                    pause();
                    break;
                case "7":
                    clear();
                    System.out.println("\t CATEGORIAS");
                    Categories.listCategories();
                    System.out.println();
                    System.out.print(" Informer a categoria -> ");
                    str = scanner.nextLine();
                    clear();
                    switch (str){
                        case "1" -> Request.getRequestsByCategory(Categories.COMBATE_A_FOME);
                        case "2" -> Request.getRequestsByCategory(Categories.CRIANÇAS);
                        case "3" -> Request.getRequestsByCategory(Categories.ENFERMOS);
                        case "4" -> Request.getRequestsByCategory(Categories.COMBATE_A_COVID_19);
                        case "5" -> Request.getRequestsByCategory(Categories.CAUSAS_AMBIENTAIS);
                        case "6" -> Request.getRequestsByCategory(Categories.SOBREVIVENTES_DE_GUERRA);
                        case "7" -> Request.getRequestsByCategory(Categories.ANIMAIS);
                        case "8" -> Request.getRequestsByCategory(Categories.SONHOS);
                        case "9" -> Request.getRequestsByCategory(Categories.POBREZA);
                        case "10" -> Request.getRequestsByCategory(Categories.OUTROS);
                    }
                    System.out.println();
                    System.out.println("Pressione [ENTER] para continuar");
                    scanner.nextLine();
                    break;
                case "8":
                    clear();
                    new Donate().createDonate();
                    break;
            }

        }while (!str.equals("0"));

        scanner.close();
    }

    private static void start() {
        new Person().createUser("nicolas", "nicolas@email.com", "123.456.789-00");
        new Institution().createUser("LucasEnterprise", "contato@lucasenterprise.com", "99.999.999/9999-99");

        Request request = new Request(
                User.getUserById(0),
                new BankAccount("999999-5", "0005"),
                Categories.OUTROS,
                "ME QUEBREI!",
                "ANDEI DE BIKE E OLHA NO QUE DEU",
                50000.);

        request = new Request(
                User.getUserById(0),
                new BankAccount("999999-5", "0005"),
                Categories.ANIMAIS,
                "Ajude um doguinho.",
                "Melhore a vida de um animalzinho",
                500.);

        request = new Request(
                User.getUserById(0),
                new BankAccount("999999-5", "0005"),
                Categories.SONHOS,
                "Quero comprar uma bike nova",
                "Após minha queda, minha bike ta meio detonada...",
                3000.);
    }

    public static void userForm () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Criar Usuario como:");
        System.out.println("1 - Pessoa fisica");
        System.out.println("2 - Pessoa juridica");
        System.out.printf(" -> ");
        String str = scanner.nextLine();
        if (str.equals("1") || str.equals("2")){
            String name, email, key;
            clear();
            if (str.equals("1")){
                System.out.printf("Nome: ");
                name = scanner.nextLine();
                System.out.printf("Email: ");
                email = scanner.nextLine();
                System.out.printf("CPF: ");
                key = scanner.nextLine();
                if (new Person().createUser(name, email, key)){
                    clear();
                    System.out.println("Usuario cadastrado!");
                    pause();
                } else {
                    clear();
                    System.out.println("Falha ao cadastrar!");
                    pause();
                }
            } else {
                System.out.printf("Nome: ");
                name = scanner.nextLine();
                System.out.printf("Email: ");
                email = scanner.nextLine();
                System.out.printf("CNPJ: ");
                key = scanner.nextLine();
                if (new Person().createUser(name, email, key)){
                    clear();
                    System.out.println("Usuario cadastrado!");
                    pause();
                } else {
                    clear();
                    System.out.println("Falha ao cadastrar!");
                    pause();
                }
            }
        }
    }

    public static Boolean requestForm( ){
        Scanner scanner = new Scanner(System.in);
        User.listPrint();
        System.out.printf("Escolha o criador da vakinha: ");
        String id = scanner.nextLine();
        if (User.getUserById(Integer.parseInt(id)) != null){
            clear();
            User user = User.getUserById(Integer.parseInt(id));
            System.out.printf("Informe numero da conta de destino: ");
            String number = scanner.nextLine();
            System.out.printf("Informe agencia: ");
            String agency = scanner.nextLine();
            BankAccount bankAccount = new BankAccount(number, agency);

            clear();
            System.out.println("\t CATEGORIAS");
            Categories.listCategories();
            System.out.println();
            System.out.print(" Informer a categoria -> ");
            number = scanner.nextLine();
            clear();
            Categories categories = Categories.OUTROS;
            switch (number){
                case "1" -> categories = Categories.COMBATE_A_FOME;
                case "2" -> categories = Categories.CRIANÇAS;
                case "3" -> categories = Categories.ENFERMOS;
                case "4" -> categories = Categories.COMBATE_A_COVID_19;
                case "5" -> categories = Categories.CAUSAS_AMBIENTAIS;
                case "6" -> categories = Categories.SOBREVIVENTES_DE_GUERRA;
                case "7" -> categories = Categories.ANIMAIS;
                case "8" -> categories = Categories.SONHOS;
                case "9" -> categories = Categories.POBREZA;
                case "10" -> categories = Categories.OUTROS;
            }

            clear();
            System.out.printf("Titulo da Vakinha: ");
            String title = scanner.nextLine();
            System.out.printf("Descricao da Vakinha: ");
            String description = scanner.nextLine();
            System.out.printf("Meta da sua Vakinha : R$ ");
            Double goal = Double.parseDouble(scanner.nextLine());
            clear();

            Request request = new Request(user, bankAccount, categories, title, description, goal);
            user.setMyRequestsList(request);
        }
        return false;

    }

    public static void clear () {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pause () {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Pressione 2x [ENTER] para continuar");
        scanner.nextLine();
    }
}
