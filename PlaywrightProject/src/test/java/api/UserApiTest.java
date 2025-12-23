package api;

import base.APIBase;
import base.TestDataManager;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import apipayload.UserCreationRequest;
import reporting.ExtentLogger;
import reporting.ExtentTestManager;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class UserApiTest extends APIBase {
	
	@BeforeClass
	public void setupBeforeClass() {
		  ExtentTestManager.startTest("TS_03 API User Creation");
	}

	@Test
	public void createUserTest() {
		
		ExtentTest testnode = ExtentTestManager.getTest().createNode("TC03 user creation with valid data");
    	testnode.assignCategory("Tc01_User_Creation_With_Valid_Data");
    	List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));
    	String name = dbData.get(0).get("name");
		String job = dbData.get(0).get("account");


		UserCreationRequest u=new UserCreationRequest();
		u.setJob(dbData.get(0).get("account"));
		u.setName( dbData.get(0).get("name"));
		UserAPI userAPI=new UserAPI(request);
		Response response = userAPI.createUser(u);

		ExtentLogger.info(testnode,"User creation request sent");
		ExtentLogger.pass(testnode,"Response status: " + response.getStatusCode());
		
		Assert.assertEquals(response.getStatusCode(), 201, "User creation failed");

		
		ExtentLogger.pass(testnode,"Response Body: " + response.getBody().prettyPrint());
		response.then().body("name", equalTo(name));
		ExtentLogger.pass(testnode,"Response Body:  " + name+" validated");
		response.then().body("job", equalTo(job));
		ExtentLogger.pass(testnode,"Response Body:  " + job+" validated");
	}
	
	
}
