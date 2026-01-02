package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthAPI {

    private final RequestSpecification request;

    public AuthAPI(RequestSpecification request) {
        this.request = request;
    }

//    public Response login(String username, String password) {
//        return request.body(PayloadBuilder.authPayload(username, password).toString())
//                .post(Endpoints.AUTH_LOGIN);
//    }
}
