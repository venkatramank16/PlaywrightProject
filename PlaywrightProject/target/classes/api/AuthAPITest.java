package api;

import base.APIBase;
import base.TestDataManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.ExtentLogger;

public class AuthApiTest extends APIBase {

    @Test
    public void loginTest() {
        TestDataManager.loadTestData("APIAuthData");
        String username = TestDataManager.get("username");
        String password = TestDataManager.get("password");

        AuthAPI authAPI = new AuthAPI(request);
        Response response = authAPI.login(username, password);

        ExtentLogger.info("Login request sent");
        ExtentLogger.pass("Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
    }
}
