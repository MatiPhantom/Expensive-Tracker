package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Service {

    List<Bill> listBills=new ArrayList<>();

    public void addBill(Bill bill) {
        listBills.add(bill);
        System.out.println("Expensive added successfully (ID: " + bill.getId() + ")");
    }

    public void listAllBills() {
        if (listBills.isEmpty()) {
            System.out.println("No bills in the list.");
        } else {
            for (Bill bill : listBills) {
                System.out.println("ID: " + bill.getId() + ", Description: " + bill.getDescription() +
                        ", Amount: " + bill.getAmount() + ", Date: " + bill.getDate());
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


}
