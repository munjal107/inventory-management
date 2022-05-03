package model;

public class InventoryItems {
    private int quantity;
    private String itemName;
    private double price;
    private String category;

    public InventoryItems(int quantity, String itemName, double price, String category) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "model.InventoryItems{" +
                "quantity=" + quantity +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
