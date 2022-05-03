import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Billing {
    public static void main(String[] args) {
        System.out.println("Hello world");

        // read inventory file
        String inventoryFileName = "src/inventory.csv";
        try{
            System.out.println("Processing Inventory.csv file");
            List<List<String>> inventoryRecords = new ReadCsvFiles().readFile(inventoryFileName);
            System.out.println(inventoryRecords);

            Inventory inventory = Inventory.getInstance();
            int index = inventory.buildInventory(inventoryRecords);
            System.out.println("index->"+index);
            inventory.showInventory();

            // now we build cards dataset
            Cards cards = Cards.getInstance();
            cards.addCards(index, inventoryRecords);
            System.out.println("done");
        }catch (Exception exception){
            System.out.println("Error->"+exception.getMessage());
        }

        // read input file
        try {
            System.out.println("processing Input cart");
            String cartFileName = "input.csv";


        }catch (Exception exception){

        }




    }



}
