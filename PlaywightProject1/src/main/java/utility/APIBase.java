package utility;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import config.PropertyUtils;

import org.testng.annotations.BeforeClass;

public class APIBase {

    protected RequestSpecification request;

//    @BeforeClass(alwaysRun = true)
//    public void setup() {
//        String baseURI = PropertyUtils.getProperty("api.base.url");
//        RestAssured.baseURI = baseURI;
//        request = RestAssured.given().log().all().contentType("application/json").header("x-api-key","reqres-free-v1");
//       
//    }
}
