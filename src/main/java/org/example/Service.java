package org.example;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class Service {
    private final  BillDAO billDao= new BillDAO();
    private List<Bill> listBills=billDao.loadData();

    public void addBill(Bill bill) {
        listBills.add(bill);
        billDao.save(listBills);
        System.out.println("Expensive added successfully (ID: " + bill.getId() + ")");
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
            billDao.save(listBills);
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
            billDao.save(listBills);
            System.out.println("Bill deleted successfully (ID: " + bill.getId() + ")");
        } else {
            System.out.println("Bill with ID " + id + " not found.");
        }
    }
}
