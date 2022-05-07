package util;

import java.io.IOException;
import java.util.List;

public interface WriteInterface {

    void writeToFile(String fileName, List<List<String>> records) throws IOException;
}
