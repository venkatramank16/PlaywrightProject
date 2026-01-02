package api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode createUserPayload(String name, String job) {
        ObjectNode node = mapper.createObjectNode();
        node.put("name", name);
        node.put("job", job);
//        node.put("password", password);
        return node;
    }

    public static ObjectNode authPayload(String username, String password) {
        ObjectNode node = mapper.createObjectNode();
        node.put("username", username);
        node.put("password", password);
        return node;
    }
}
