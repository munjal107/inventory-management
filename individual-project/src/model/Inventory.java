package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private static Inventory instance = null;

    public Map<String, InventoryItems> inventory;

    private Inventory(){

//        System.out.println("private constructor of inventory...");
    }

    public static Inventory getInstance(){
        if (instance == null)
            instance = new Inventory();
        return instance;
    }

    public int buildInventory(List<List<String>> inventoryRecords){
//        int index = 2;
//        System.out.println("building inventory...");
        this.inventory = new HashMap<>();

        int i =2;
        while (true){
//            System.out.println("Inside while...");
            if(inventoryRecords.get(i).size()<4){
                break;
            }
            List<String> stringList = inventoryRecords.get(i);

            String itemName = stringList.get(0).strip().toLowerCase();
            String category = stringList.get(1);
            int qty = Integer.parseInt(stringList.get(2));
            double price = Double.parseDouble(stringList.get(3));

            InventoryItems item = new InventoryItems(qty, itemName, price, category);
//            System.out.println("inventory item +"+item);

            this.inventory.put(itemName, item);
            i++;
        }
        return i;
    }

    public void showInventory(){
        System.out.println("Showing model.Inventory....");
        for(String s:this.inventory.keySet()){
            System.out.println(this.inventory.get(s));
        }
    }


}
