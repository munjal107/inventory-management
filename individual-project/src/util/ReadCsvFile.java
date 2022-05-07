package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCsvFile {
    // read a csv file
    public List<List<String>> readFile(String fileName){
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
//                System.out.println(Arrays.asList(values));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        return records;
    }
}
