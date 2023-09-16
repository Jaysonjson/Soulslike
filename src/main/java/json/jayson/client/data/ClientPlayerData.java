package json.jayson.client.data;

import java.util.HashMap;

public class ClientPlayerData {
    private static HashMap<String, Integer> attributes;
    private static int level;

    public static void set(HashMap<String, Integer> attributes, int level) {
        ClientPlayerData.attributes = attributes;
        ClientPlayerData.level = level;
    }

    public static void setLevel(int level) {
        ClientPlayerData.level = level;
    }

    public static HashMap<String, Integer> getAttributes() {
        return attributes;
    }

    public static int getLevel() {
        return level;
    }
}
