package com.dbc.application;

import com.dbc.entities.BankAccount;
import com.dbc.entities.Category;
import com.dbc.entities.Request;
import com.dbc.entities.user.User;
import com.dbc.repository.CategoryRepository;
import com.dbc.repository.UserRepository;
import com.dbc.service.BankAccountService;
import com.dbc.service.CategoryService;
import com.dbc.service.RequestService;
import com.dbc.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

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
                    new UserService().list();
                    pause();
                    break;
                case "3":
                    clear();
                    new UserService().list();
                    System.out.println();
                    String idUpdate = scanner.nextLine();
                    User user = new UserRepository().getUserById(Integer.parseInt(idUpdate));
                    if (user != null){
                        System.out.printf("Nome: ");
                        user.setName(scanner.nextLine());
                        System.out.printf("Email: ");
                        user.setEmail(scanner.nextLine());
                        System.out.printf("Senha: ");
                        user.setPassword(scanner.nextLine());

                        new UserService().update(user.getIdUser(), user);
                    }
                    break;
                case "4":
                    clear();
                    new UserService().list();
                    System.out.println();
                    System.out.printf("Id do Usuario que deseja remover: ");
                    String idRemove = scanner.nextLine();

                    new UserService().remove(Integer.parseInt(idRemove));
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

    public static void userForm () throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Criar Usuario como:");
        System.out.println("1 - Pessoa fisica");
        System.out.println("2 - Pessoa juridica");
        System.out.printf(" -> ");
        String str = scanner.nextLine();
        if (str.equals("1") || str.equals("2")){
            String name, email, pass, key;
            clear();
            if (str.equals("1")){
                System.out.printf("Nome: ");
                name = scanner.nextLine();
                System.out.printf("Email: ");
                email = scanner.nextLine();
                System.out.printf("Senha: ");
                pass = scanner.nextLine();
                System.out.printf("CPF: ");
                key = scanner.nextLine();
                if (new UserService().add(new User(name, email, pass, 1, key))){
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
                System.out.printf("Senha: ");
                pass = scanner.nextLine();
                System.out.printf("CNPJ: ");
                key = scanner.nextLine();
                if (new UserService().add(new User(name, email, pass, 2, key))){
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

    public static void requestForm( ) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        new UserService().list();
        System.out.printf("Escolha o criador da vakinha: ");
        String id = scanner.nextLine();

        clear();
        User user = new UserRepository().getUserById(Integer.parseInt(id));
        System.out.printf("Informe numero da conta de destino: ");
        String number = scanner.nextLine();
        System.out.printf("Informe agencia: ");
        String agency = scanner.nextLine();
        BankAccount account = new BankAccount();
        account.setAccount_number(number);
        account.setAgency(agency);
        new BankAccountService().add(account);

        clear();
        System.out.println("\t CATEGORIAS");
        new CategoryService().list();
        System.out.println();
        System.out.print(" Informer a categoria -> ");
        number = scanner.nextLine();

        Category category = new CategoryRepository().getCategoryById(Integer.parseInt(id));

        clear();
        System.out.printf("Titulo da Vakinha: ");
        String title = scanner.nextLine();
        System.out.printf("Descricao da Vakinha: ");
        String description = scanner.nextLine();
        System.out.printf("Meta da sua Vakinha : R$ ");
        Double goal = Double.parseDouble(scanner.nextLine());
        clear();

        Request request = new Request();
        request.setUser(user);
        request.setAccount(account);
        request.setDescription(description);
        request.setCategory(category);
        request.setGoal(goal);
        request.setTitle(title);

        new RequestService().add(request);
    }

    public static void clear() {
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
