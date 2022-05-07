package util;

import model.CartItems;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteErrorFile implements WriteInterface {
//    public void writeErrorFile(String fileName, List<CartItems> cartItemsList) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
//        writer.write("Please correct quantities.\n");
//        for(CartItems item:cartItemsList){
//            writer.write(item.getItemName());
//            writer.write("\n");
//        }
//        writer.close();
//    }

    @Override
    public void writeToFile(String fileName, List<List<String>> records) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("Please correct quantities.\n");
        for(List<String> record: records){
            writer.write(record.get(0));
            writer.write("\n");
        }
        writer.close();
    }
}
