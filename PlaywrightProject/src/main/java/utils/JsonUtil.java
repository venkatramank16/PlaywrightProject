package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonUtil {

    public static JsonNode readJsonFile(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
