package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;
import utility.APIBase;

import static org.hamcrest.Matchers.*;

public class UserApiTest extends APIBase {

	@Test
	public void createUserTest() {
//        TestDataManager.loadTestData("APIUserData");
//        String name = TestDataManager.get("name");
//        String job = TestDataManager.get("job");
		String name = "Venkat";
		String job = "Test Engineer";

//        String password = TestDataManager.get("password");

		UserAPI userAPI = new UserAPI(request);
		//Response response = userAPI.createUser(name, job);

//		ExtentLogger.info("User creation request sent");
//		ExtentLogger.pass("Response status: " + response.getStatusCode());
//		
//		Assert.assertEquals(response.getStatusCode(), 201, "User creation failed");
//
//		
//		ExtentLogger.pass("Response Body: " + response.getBody().prettyPrint());
//		response.then().body("name", equalTo(name));
//		ExtentLogger.pass("Response Body:  " + name+" validated");
//		response.then().body("job", equalTo(job));
//		ExtentLogger.pass("Response Body:  " + job+" validated");
	}
	
	
}
