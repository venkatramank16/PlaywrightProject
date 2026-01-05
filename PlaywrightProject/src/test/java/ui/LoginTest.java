package ui;

import base.RetryAnalyzer;
import base.TestDataManager;
import config.ConfigReader;

import java.util.List;
import java.util.Map;

import listeners.Scenario;
import listeners.TestListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.LoginPage;
import reporting.ExtentLogger;
import reporting.ExtentTestManager;
@Scenario("Login Feature – User Authentication")
public class LoginTest extends BaseTest {


	
	@BeforeClass
	public void setupBeforeClass() {
		 // ExtentTestManager.startTest("TS_01 Login Functionality Validation");
	}

    @Test(priority = 4,description = "TC01 Login with valid credentials")
    public void loginwithValidCredentialsTest() {
    	//ExtentTest testnode = ExtentTestManager.getTest().createNode("TC01 Login with valid credentials");
    	//testnode.assignCategory("Tc01_login_with_Valid_Credentials");
        // Load Excel data
   	 List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));

       String username=dbData.get(0).get("username");
       String password=dbData.get(0).get("password");
       
       LoginPage loginPage = new LoginPage(page);
       loginPage.navigateTo(ConfigReader.getProperty("base.url"));

       ExtentLogger.info(TestListener.testNode.get(),"Navigated to login page");
       loginPage.login(username, password);
       ExtentLogger.pass(TestListener.testNode.get(),"Username entered as: "+username);
		ExtentLogger.pass(TestListener.testNode.get(),"Password entered as: "+password);

       ExtentLogger.pass(TestListener.testNode.get(),"Login performed with user: " + username);
       ExtentLogger.addScreenshot(TestListener.testNode.get(),loginPage.captureScreenshot(Thread.currentThread()
               .getStackTrace()[1]
               .getMethodName()));
    }
    
    @Test(priority = 5,description = "TC02 Login with invalid credentials")
    public void loginwithInValidCredentialsTest() {
    	//ExtentTest testnode = ExtentTestManager.getTest().createNode("TC02 Login with invalid credentials");
    	//testnode.assignCategory("Tc02_login_with_InValid_Credentials");
        // Load Excel data
    	 List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));

        String username=dbData.get(1).get("username");
        String password=dbData.get(1).get("password");
        
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo(ConfigReader.getProperty("base.url"));

        ExtentLogger.info(TestListener.testNode.get(),"Navigated to login page");
        loginPage.login(username, password);
        ExtentLogger.pass(TestListener.testNode.get(),"Username entered as: "+username);
		ExtentLogger.pass(TestListener.testNode.get(),"Password entered as: "+password);

        ExtentLogger.pass(TestListener.testNode.get(),"Login performed with user: " + username);
        ExtentLogger.addScreenshot(TestListener.testNode.get(),loginPage.captureScreenshot(Thread.currentThread()
                .getStackTrace()[1]
                .getMethodName()));
    }
}
