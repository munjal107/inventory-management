package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    List<CartItems> cartItemsList;
    private Double cardNumber;

    private static Cart instance;
    private Cart(){}

    public static Cart getInstance(){
        if(instance==null){
            instance = new Cart();
        }
        return instance;
    }

    public Double getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Double cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void buildCart(List<List<String>> cartRecords){
//        System.out.println("Building cart...");
        this.cartItemsList = new ArrayList<>();
        int index = 1;
        while (index<cartRecords.size()){
            List<String> record = cartRecords.get(index);

            if(record.size()==3){
                this.cardNumber = Double.valueOf(record.get(2));
            }

            String itemName = record.get(0);
            int qty = Integer.parseInt(record.get(1));
            CartItems item = new CartItems(itemName, qty);

            this.cartItemsList.add(item);

            index++;
        }
//        this.showCart();

    }

    public void showCart(){
        System.out.println("Showing User cart...");
        System.out.println("Showing Items...");
        for(CartItems c:this.cartItemsList){
            System.out.println(c);
        }
        System.out.println("User Card Number -> "+this.cardNumber);
    }

    public Map<String, List<CartItems>> validateCart(Inventory inventory, int maxEssentialItems,int maxLuxuryItems, int maxMiscItems){
        Map<String, List<CartItems>> listMap = new HashMap<>();
        List<CartItems> invalidItemsList = new ArrayList<>();

        int essentialItemCount = 0;
        int luxuryItemCount = 0;
        int miscItemCount = 0;

        for(CartItems cartItem: this.cartItemsList){
            String cartItemName = cartItem.getItemName().strip().toLowerCase();
            int itemQty = cartItem.getQuantity();

            InventoryItems inventoryItem = inventory.inventory.get(cartItemName);

            // logic
//            if((inventoryItem.getCategory().equalsIgnoreCase("Essentials") && itemQty>maxEssentialItems)
//                    || inventoryItem.getQuantity()<itemQty){
//                invalidItemsList.add(cartItem);
//            }else if((inventoryItem.getCategory().equalsIgnoreCase("Luxury") && itemQty>maxLuxuryItems)
//                    || inventoryItem.getQuantity()<itemQty){
//                invalidItemsList.add(cartItem);
//            }else if((inventoryItem.getCategory().equalsIgnoreCase("Misc") && itemQty>maxMiscItems)
//                    || inventoryItem.getQuantity()<itemQty){
//                invalidItemsList.add(cartItem);
//            }

//            boolean isItemAdded=false;
//            if(inventoryItem.getQuantity()<itemQty){
//                invalidItemsList.add(cartItem);
//                isItemAdded=true;
//            }
//            if(inventoryItem.getCategory().equalsIgnoreCase("Essentials")){
//                essentialItemCount++;
//            }else if(inventoryItem.getCategory().equalsIgnoreCase("Luxury")){
//                luxuryItemCount++;
//            }else if(inventoryItem.getCategory().equalsIgnoreCase("Misc")){
//                miscItemCount++;
//            }
//
//            if(essentialItemCount>maxEssentialItems || luxuryItemCount>maxLuxuryItems || miscItemCount>maxMiscItems){
//                if(isItemAdded==false){
//                    invalidItemsList.add(cartItem);
//                }
//            }
            if(inventoryItem.getQuantity()<itemQty){
                invalidItemsList.add(cartItem);
            }else{

                if(inventoryItem.getCategory().equalsIgnoreCase("Essentials")){
                    essentialItemCount+=itemQty;
                    if(essentialItemCount>maxEssentialItems){
                        invalidItemsList.add(cartItem);
                    }
                }else if(inventoryItem.getCategory().equalsIgnoreCase("Luxury")){
                    luxuryItemCount+=itemQty;
                    if(luxuryItemCount>maxLuxuryItems){
                        invalidItemsList.add(cartItem);
                    }
                }else if(inventoryItem.getCategory().equalsIgnoreCase("Misc")){
                    miscItemCount+=itemQty;
                    if(miscItemCount>maxMiscItems){
                        invalidItemsList.add(cartItem);
                    }
                }
            }

//                if(isItemAdded==false){
//                    invalidItemsList.add(cartItem);
//                }




            //check if any error
            if(invalidItemsList.size()>0){
                listMap.put("error", invalidItemsList);
            }else {
                listMap.put("success", this.cartItemsList);
            }

        }
        return listMap;
    }

    public List<List<String>> generateBill(Inventory inventory){
        List<List<String>> records = new ArrayList<>();

        // file header
        List<String> columns = new ArrayList<>();
        columns.add("Item");
        columns.add("Quantity");
        columns.add("Price");
        columns.add("TotalPrice");

        records.add(columns);

        double totalPrice = 0.0;
//        int index=0;
        for(CartItems item: this.cartItemsList){
            List<String> values = new ArrayList<>();
            String itemName = item.getItemName();
            int qty = item.getQuantity();

            InventoryItems inventoryItem = inventory.inventory.get(itemName.toLowerCase().strip());

            double itemPrice = qty*inventoryItem.getPrice();
            totalPrice = totalPrice + itemPrice;
            values.add(itemName);
            values.add(String.valueOf(qty));
            values.add(String.valueOf(itemPrice));

            records.add(values);

        }

        List<List<String>> newRecords = new ArrayList<>();
        int index=0;
        for(List<String> record: records){
            if(index==1){
                record.add(String.valueOf(totalPrice));
            }
            index++;
            newRecords.add(record);
        }

        return newRecords;
    }



}
