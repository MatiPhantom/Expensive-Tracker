package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static final Service service= new Service();


    public static void main(String[] args) {

        System.out.print("$ expensive-tracker ");

        Scanner e = new Scanner(System.in);
        while (e.hasNext()) {
            //Leyendo token
            String line = e.nextLine();
            String[] arguments = line.trim().split(" ");

            if (!UtilCommands.validateCommands(arguments)) {
                continue;
            }

            switch (arguments[0]) {
                case "add":
                    boolean descriptionValid = arguments[2].startsWith("\"") && arguments[2].endsWith("\"");
                    if (descriptionValid && UtilCommands.validateTokensAdd(arguments)) {
                        String description = arguments[2].substring(1, arguments[2].length() - 1);
                        double amount = Double.parseDouble(arguments[4]);
                        Bill bill = new Bill(description, amount);
                        service.addBill(bill);
                    }
                    break;
                case "list":
                    service.listAllBills();
                    break;
                case "summary":
                    if (UtilCommands.validateTokensSummary(arguments)) {
                        switch (arguments[1]) {
                            case "--month":
                                if (arguments.length == 3) {
                                    int month = Integer.parseInt(arguments[2]);
                                    service.summaryForMonth(month);
                                } else {
                                    System.out.println("Invalid number of arguments for summary by month. Expected 2 arguments.");
                                }
                                break;
                            case "--year":
                                if (arguments.length == 3) {
                                    int year = Integer.parseInt(arguments[2]);
                                    service.summaryForYear(year);
                                } else {
                                    System.out.println("Invalid number of arguments for summary by year. Expected 2 arguments.");
                                }
                                break;
                            default:
                                System.out.println("Invalid token for summary. Available tokens: " + UtilCommands.TOKENS_SUMMARY);
                        }
                    } else {
                        service.allSummary();
                    }
                    break;
                case "update":
                    if (UtilCommands.validateTokensUpdate(arguments)) {
                        service.updateBill(Integer.parseInt(arguments[2]),
                                arguments[4].substring(1, arguments[4].length() - 1),
                                Double.parseDouble(arguments[6]));
                    } else {
                        System.out.println("Invalid number of arguments for update command. Expected 5 arguments.");
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
                    System.out.println("Invalid command. Available commands: " + UtilCommands.COMMANDS);
            }
            System.out.print("$ expensive-tracker ");
        }
    }
}