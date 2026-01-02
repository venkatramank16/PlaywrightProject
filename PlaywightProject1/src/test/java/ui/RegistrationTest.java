package ui;

import org.testng.annotations.Test;

import config.PropertyUtils;
import pages.RegistrationPage;
import utility.RetryAnalyzer;

public class RegistrationTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void registrationTest() throws Throwable {
        //TestDataManager.loadTestData("RegistrationData");
//        String name = TestDataManager.get("name");

        RegistrationPage registrationPage = new RegistrationPage(page);
        registrationPage.navigateTo(testProperties.getProperty("base.url"));

       // ExtentLogger.info("Navigated to Regstration page");
        registrationPage.registarion("venkat","India","Engineer","venkat@gmail.com","venkat@123","venkat@123");
//        String result = registrationPage.getSearchResult();
       // ExtentLogger.pass("Registration Completed");
        //ExtentLogger.addScreenshot(registrationPage.captureScreenshot("RegistrationPage"));
    }
}
