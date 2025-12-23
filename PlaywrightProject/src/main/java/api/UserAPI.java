package api;

import apipayload.UserCreationRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserAPI {

    private final RequestSpecification request;

    public UserAPI(RequestSpecification request) {
        this.request = request;
    }

    public Response createUser(UserCreationRequest req) {
        return request.body(req)
                .post(Endpoints.USER_CREATE);
    }

    public Response updateUser(String id, String name, String job) {
        return request.body(PayloadBuilder.createUserPayload(name, job).toString())
                .put(Endpoints.USER_UPDATE.replace("{id}", id));
    }

    public Response deleteUser(String id) {
        return request.delete(Endpoints.USER_DELETE.replace("{id}", id));
    }
}
