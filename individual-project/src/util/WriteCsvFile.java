package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteCsvFile implements WriteInterface {
    // write to csv file
    public void writeToFile(String fileName, List<List<String>> records) throws IOException {
        File newFile = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
        for(List<String> record: records){
            for(String col: record){
                writer.write(col);
                writer.write(",");
            }
            writer.write("\n");
        }

        writer.close();

        return;
    }
}
