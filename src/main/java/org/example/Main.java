package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static final Service service= new Service();
    static Set<String> COMMANDS= Set.of( "add", "delete", "list", "summary", "update" );
    static Set<String> TOKENS_ADD = Set.of( "--description", "--amount");
    static Set<String> TOKENS_UPDATE = Set.of( "--id", "--description", "--amount");
    static Set<String> TOKENS_SUMMARY = Set.of( "--month", "--year");

    public static void main(String[] args) {

        System.out.print("$ expensive-tracker ");

        Scanner e = new Scanner(System.in);
        while(e.hasNext()){
            //Leyendo token
            String line=e.nextLine();
            String[] arguments = line.trim().split(" ");

            if(!COMMANDS.contains(arguments[1])){
                System.out.println("Invalid command. Available commands: " + COMMANDS);
                continue;
            }

            switch (arguments[1]) {
                case "add":
                    boolean lengthValid=arguments.length==5;
                    boolean descriptionValid=arguments[2].startsWith("\"") && arguments[2].endsWith("\"");
                    boolean lineValid=arguments[1]=="--description" || arguments[3]=="--amount";
                    if(lengthValid || descriptionValid || lineValid){
                        String description = arguments[2].substring(1, arguments[2].length() - 1);
                        double amount = Double.parseDouble(arguments[4]);
                        Bill bill = new Bill(description, amount);
                        service.addBill(bill);
                    } else {
                        System.out.println("Missing required arguments: --description and --amount");
                    }
                    break;
                case "list":
                    service.listAllBills();
                    break;
                case "summary":
                    if(Arrays.stream(arguments).anyMatch(TOKENS::contains)){

                    } else {
                        System.out.println("Missing required arguments: --month or --year");
                    }
                    break;
                case "update":
                    if (arguments.length>7){
                        System.out.println("Invalid number of arguments for update command. Expected 5 arguments.");
                        break;
                    }
                    if(arguments.length==5){
                        switch(arguments[3]){
                            case "--description" -> {
                                int id= Integer.parseInt(arguments[1]);
                                descriptionValid=arguments[2].startsWith("\"") && arguments[2].endsWith("\"");
                                String description = arguments[2].substring(1, arguments[2].length() - 1);
                            }
                            case "--amount" -> {

                            }
                            default -> {
                                System.out.println("Invalid command. Available commands: " + COMMANDS);
                            }
                        }
                    }else{
                        System.out.println("Invalid command. Use: update --id <bill_id> --description <description> --amount <amount>");
                    }
                    break;
                case "delete":
                    if (arguments.length != 3 || !arguments[1].equals("--id")) {
                        System.out.println("Invalid command. Use: delete --id <bill_id>");
                        break;
                    }
                    int idToDelete = Integer.parseInt(arguments[2]);
                    service.deleteBill(idToDelete);
                    break;
                default:
                    System.out.println("Invalid command. Available commands: " + COMMANDS);
        }

    }




}