package org.example;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Service {
    List<Bill> listBills=new ArrayList<>();

    public void addBill(Bill bill) {
        listBills.add(bill);
        UtilJson.save(listBills);
        System.out.println("Expensive added successfully (ID: " + bill.getId() + ")");
    }

    public void validateArgumentsAdd(String[] arguments) {
        boolean lengthValid = arguments.length == 5;
        boolean descriptionValid = arguments[2].startsWith("\"") && arguments[2].endsWith("\"");
        boolean lineValid = arguments[1].equals("--description") || arguments[3].equals("--amount");

        if (lengthValid && descriptionValid && lineValid) {
            String description = arguments[2].substring(1, arguments[2].length() - 1);
            double amount = Double.parseDouble(arguments[4]);
            Bill bill = new Bill(description, amount);
            addBill(bill);
        } else {
            System.out.println("Missing required arguments: --description and --amount");
        }
    }

    public void listAllBills() {
        if (listBills.isEmpty()) {
            System.out.println("No bills in the list.");
        } else {
            System.out.println("# ID\t Description\t\tAmount\t\tDate");
            for (Bill bill : listBills) {
                System.out.println("# "+bill.getId() + "\t" + bill.getDescription() +
                        "\t" + bill.getAmount() + "\t" + bill.getDate());
            }
        }
    }

    public void allSummary() {
        if (listBills.isEmpty()) {
            System.out.println("No bills to summarize.");
        } else {
            double totalAmount = listBills.stream().mapToDouble(Bill::getAmount).sum();
            System.out.println("Total Amount: " + totalAmount);
        }
    }

    public void summaryForMonth(int month) {
        double totalAmount = listBills.stream()
                .filter(bill -> bill.getDate().getMonthValue() == month).mapToDouble(Bill::getAmount).sum();
        System.out.println("Total Amount for " + month +" : " + totalAmount);
    }

    public void summaryForYear(int year){
        double amuntYear= listBills.stream().filter(bill -> bill.getDate().getYear()== year).mapToDouble(Bill::getAmount).sum();
    }

    public void updateBill(int id, String description, double amount) {
        Bill bill = getBill(id);
        if (bill != null) {
            bill.setDescription(description);
            bill.setAmount(amount);
            System.out.println("Bill updated successfully (ID: " + bill.getId() + ")");
        } else {
            System.out.println("Bill with ID " + id + " not found.");
        }
    }

    public Bill getBill(int id) {
        return listBills.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public void deleteBill(int id) {
        Bill bill = getBill(id);
        if (bill != null) {
            listBills.remove(bill);
            System.out.println("Bill deleted successfully (ID: " + bill.getId() + ")");
        } else {
            System.out.println("Bill with ID " + id + " not found.");
        }
    }

    public void save() {
        try(FileWriter file = new FileWriter("bills.json")) {
            for (Bill bill : listBills) {
                file.write(bill.getId() + "," + bill.getDescription() + "," + bill.getAmount() + "," + bill.getDate() + "\n");
            }
            System.out.println("Bills saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving bills: " + e.getMessage());
        }
    }


}
