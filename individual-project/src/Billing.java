import model.Cards;
import model.Cart;
import model.CartItems;
import model.Inventory;
import util.ReadCsvFile;
import util.WriteCsvFile;
import util.WriteErrorFile;
import util.WriteInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Billing {
    public static void main(String[] args) {
//        System.out.println("Hello world");

        // read inventory file
//        String inventoryFileName = "src/inventory.csv";
        String inventoryFileName = "Inventory.csv";

        Inventory inventory = Inventory.getInstance();
        Cards cards = Cards.getInstance();
        try{
            System.out.println("Processing Inventory.csv file");
//            List<List<String>> inventoryRecords = new ReadAndWriteFiles().readFile(inventoryFileName);
            List<List<String>> inventoryRecords = new ReadCsvFile().readFile(inventoryFileName);
//            System.out.println(inventoryRecords);

            int index = inventory.buildInventory(inventoryRecords);
//            System.out.println("index->"+index);
//            inventory.showInventory();

            // now we build cards dataset
            cards.addCards(index, inventoryRecords);
            System.out.println("done Processing Inventory.csv file");
        }catch (Exception exception){
            System.out.println("Error processing inventory.csv->"+exception.getMessage());
        }

        // read input file
        try {
//            System.out.println("processing Input cart");
//            String cartFileName = "src/Input.csv";
            String cartFileName = "Input.csv";

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Input File name : ");
            cartFileName = reader.readLine();
            cartFileName = cartFileName.strip();

//            List<List<String>> cartRecords = new ReadAndWriteFiles().readFile(cartFileName);
            List<List<String>> cartRecords = new ReadCsvFile().readFile(cartFileName);

            Cart cart = Cart.getInstance();
            cart.buildCart(cartRecords);
//            cart.showCart();

            // process cart to validate cart
            int maxEssentialItems = 3;
            int maxLuxuryItems = 4;
            int maxMiscItems = 6;
            Map<String, List<CartItems>> orderItems = cart.validateCart(inventory, maxEssentialItems, maxLuxuryItems, maxMiscItems);

            //if error
            if(orderItems.getOrDefault("error", null)!=null){
                //TO-DO
                System.out.println("Cart Invalid - generating output.txt error file");
                List<List<String>> errors = new ArrayList<>();
                for(CartItems item:orderItems.get("error")){
                    List<String> temp = new ArrayList<>();
                    temp.add(item.getItemName());
                    errors.add(temp);
                }

//                String ouputFileName = "src/output.txt";
                String ouputFileName = "output.txt";
                WriteInterface writeInterface = new WriteErrorFile();
                writeInterface.writeToFile(ouputFileName, errors);

//                new ReadAndWriteFiles().writeErrorFile(ouputFileName, orderItems.get("error"));
            }else{
                // generate Bill
                List<List<String>> records = cart.generateBill(inventory);
                // add the card if not present in system
                cards.addCard(cart.getCardNumber());
//                cards.showCards();
                System.out.println("Cart Valid - generating output.csv file");
                System.out.println(records);

                //write
//                String ouputFileName = "src/output.csv";
                String ouputFileName = "output.csv";
                WriteInterface writeInterface = new WriteCsvFile();
                writeInterface.writeToFile(ouputFileName, records);

            }



        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }




    }



}
