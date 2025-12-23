package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import reporting.ExtentTestManager;

public class APIBase {

    protected RequestSpecification request;

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        String baseURI = ConfigReader.getProperty("api.base.url");
        RestAssured.baseURI = baseURI;
        request = RestAssured.given().log().all().contentType("application/json").header("x-api-key","reqres_7b1d19b2df8443cface4eb9d86f7d593");
       
    }
    
    @AfterSuite
    public void endSuite() {
    	  ExtentTestManager.endTest();
    }
}
