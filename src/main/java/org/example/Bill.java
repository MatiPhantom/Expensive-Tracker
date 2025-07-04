package org.example;

import java.time.LocalDate;

public class Bill {
    private static int COUNT= 1;

    private int id;
    private String description;
    private LocalDate date = LocalDate.now();
    private double amount;

    public Bill(String description, double amount) {
        this.id=COUNT++;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCOUNT(int count) {
        COUNT = count;
    }
}
