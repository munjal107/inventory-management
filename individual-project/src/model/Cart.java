package model;

import java.util.ArrayList;
import java.util.List;

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

    public void buildCart(List<List<String>> cartRecords){
        System.out.println("Building cart...");
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
        this.showCart();

    }

    public void showCart(){
        System.out.println("Showing User cart...");
        System.out.println("Showing Items...");
        for(CartItems c:this.cartItemsList){
            System.out.println(c);
        }
        System.out.println("\nUser model.Cart Number -> "+this.cardNumber);
    }


}
