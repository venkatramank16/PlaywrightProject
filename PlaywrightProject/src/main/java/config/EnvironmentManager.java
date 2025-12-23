package config;

import java.util.Properties;

public class EnvironmentManager {

    private static Properties envProperties;

    public static void setEnvironment(String env) {
        String path = "src/main/resources/" + env.toLowerCase() + ".properties";
        try {
            envProperties = PropertyUtils.loadProperties(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEnvProperty(String key) {
        if (envProperties == null) {
            throw new RuntimeException("Environment not set. Call EnvironmentManager.setEnvironment(env) first.");
        }
        return envProperties.getProperty(key);
    }
}
