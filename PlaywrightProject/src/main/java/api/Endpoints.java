package api;

import config.ConfigReader;

public class Endpoints {

    public static final String BASE_URL = ConfigReader.getProperty("api.base.url");

    public static final String USER_CREATE = BASE_URL + "api/users";
    public static final String USER_UPDATE = BASE_URL + "api/users/{id}";
    public static final String USER_DELETE = BASE_URL + "api/users/{id}";
    public static final String AUTH_LOGIN = BASE_URL + "api/auth/login";
}
