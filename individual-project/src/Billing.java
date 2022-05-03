import model.Cards;
import model.Cart;
import model.Inventory;

import java.util.List;

public class Billing {
    public static void main(String[] args) {
        System.out.println("Hello world");

        // read inventory file
        String inventoryFileName = "src/inventory.csv";
        try{
            System.out.println("Processing model.Inventory.csv file");
            List<List<String>> inventoryRecords = new ReadCsvFiles().readFile(inventoryFileName);
            System.out.println(inventoryRecords);

            Inventory inventory = Inventory.getInstance();
            int index = inventory.buildInventory(inventoryRecords);
            System.out.println("index->"+index);
            inventory.showInventory();

            // now we build cards dataset
            Cards cards = Cards.getInstance();
            cards.addCards(index, inventoryRecords);
            System.out.println("done Processing model.Inventory.csv file");
        }catch (Exception exception){
            System.out.println("Error->"+exception.getMessage());
        }

        // read input file
        try {
            System.out.println("processing Input cart");
            String cartFileName = "src/Input.csv";

            List<List<String>> cartRecords = new ReadCsvFiles().readFile(cartFileName);

            Cart cart = Cart.getInstance();
            cart.buildCart(cartRecords);

            // process cart to validate cart



        }catch (Exception exception){

        }




    }



}
