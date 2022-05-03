import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCsvFiles {

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


    public  List<String> readFileRecords(String fileName) throws IOException {
        List<String> result = new ArrayList<>();
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
