package ui;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import utility.RetryAnalyzer;

public class LoginTest extends BaseTest {
	
	@BeforeClass
	public void setupBeforeClass() {
		extentTest = reporter.createTest("LoginTestValidation", "Verify login functionality of Demo App");
	}

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginTest() {
        // Load Excel data
        //TestDataManager.loadTestData("LoginData");
    	testNode = extentTest.createNode("LoginWithValidCredentials");
        //String username = TestDataManager.get("username");
        //String password = TestDataManager.get("password");

        String username="qa_testers@qabrains.com";
        String password="Password123";
        
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo(testProperties.getProperty("base.url"));

        loginPage.login(username, password);

    }
}
