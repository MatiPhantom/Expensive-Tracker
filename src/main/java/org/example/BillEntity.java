package org.example;

import java.time.LocalDate;

public class BillEntity {

    private int id;
    private String description;
    private String date;
    private double amount;

    public BillEntity(int id, String description, String date, double amount) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }
}
