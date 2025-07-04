package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.nio.file.Path;
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
            file.flush();
        }catch(Exception e){
            System.out.println("Error saving bills to JSON: " + e.getMessage());
        }
    }

    public List<Bill> loadData() {
        try {
            Path path = Path.of("bills.json");
            List<BillEntity> listEntity=List.of(mapper.readValue(path.toFile(), BillEntity[].class));
            List<Bill> listBill = listEntity.stream().map(
                    entity -> new Bill(entity.getDescription(), entity.getAmount())
            ).toList();
            // Actualizar el ID estático de la clase Bill
            if (!listBill.isEmpty()) {
                int maxId = listBill.stream().mapToInt(Bill::getId).max().orElse(1);
                Bill.setCOUNT(maxId + 1);
                System.out.println("Data loaded successfully.");
            }
            return listBill;
        } catch (Exception e) {
            System.out.println("Error loading bills from JSON: " + e.getMessage());
            return List.of();
        }
    }
}
