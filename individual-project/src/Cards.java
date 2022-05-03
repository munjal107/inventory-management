import java.util.HashSet;
import java.util.List;

public class Cards {
    private static Cards instance = null;
    HashSet<Double> hashSet;

    private Cards(){
        System.out.println("private constructor of cards...");
    }

    public static Cards getInstance(){
        if(instance==null){
            instance = new Cards();
        }
        return instance;
    }

    public void addCards(int index, List<List<String>> inventoryRecords){

//        System.out.println("index cards "+inventoryRecords.get(index));

        while (index<inventoryRecords.size()){

            if(inventoryRecords.get(index).size()>0 && inventoryRecords.get(index).get(0).equalsIgnoreCase("cardnumber")){
                break;
            }
            index++;
        }

//        System.out.println("index after break..."+index);
        this.hashSet = new HashSet<>();
        index++;
        while (index<inventoryRecords.size()){
            Double d = Double.valueOf(inventoryRecords.get(index).get(0));
            hashSet.add(d);
            index++;
        }

        System.out.println("-- Printing Card Data --");
        for (Double ele : this.hashSet) {
            // Print HashSet data
//            double d = 5410000000000000.0;
//            System.out.println(ele==d);
            System.out.println(ele);
        }

    }





}
