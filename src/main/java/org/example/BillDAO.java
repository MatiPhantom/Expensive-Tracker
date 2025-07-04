package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.util.List;

public class BillDAO {

    //Utilizando la librería Jackson para serializar y deserializar objetos a JSON.
    private static final ObjectMapper mapper=new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public void save(List<Bill> listBill){
        List<BillEntity> listBillEntity= listBill.stream().map(
                bill -> new BillEntity(bill.getId(), bill.getDescription(), bill.getDate().toString(), bill.getAmount())
        ).toList();
        try(FileWriter file = new FileWriter("bills.json")) {
            String listJson= mapper.writeValueAsString(listBillEntity);
            file.write(listJson);
        }catch(Exception e){
            System.out.println("Error saving bills to JSON: " + e.getMessage());
        }
    }
}
