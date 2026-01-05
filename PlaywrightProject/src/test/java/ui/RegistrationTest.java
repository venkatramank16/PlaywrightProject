package ui;

import base.RetryAnalyzer;
import base.TestDataManager;
import config.ConfigReader;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import listeners.Scenario;
import listeners.TestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.RegistrationPage;
import reporting.ExtentLogger;
import reporting.ExtentTestManager;
@Scenario("Registration Feature")
public class RegistrationTest extends BaseTest {

	@BeforeClass
	public void setupBeforeClass() {
		 // ExtentTestManager.startTest("TS_02 Registration Functionality Validation");
	}
	
	@Test(priority  =1,description ="TC01 Registratioin with valid data")
	public void registrationwithValidDataTest() throws Throwable {
		
		//ExtentTest testnode = ExtentTestManager.getTest().createNode("TC01 Registratioin with valid data");
    	//testnode.assignCategory("Tc01_Registration_Valid_Data");

		RegistrationPage registrationPage = new RegistrationPage(page);
		registrationPage.navigateTo(ConfigReader.getProperty("base.url"));
List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));
		ExtentLogger.info(TestListener.testNode.get(),"Navigated to Regstration page");
		registrationPage.registarion(dbData.get(0).get("First_Name"), dbData.get(0).get("Last_Name"), dbData.get(0).get("Address"), dbData.get(0).get("city"), dbData.get(0).get("state"), dbData.get(0).get("zip_code"),dbData.get(0).get("phone_number"),dbData.get(0).get("ssn"),dbData.get(0).get("password"));
//        String result = registrationPage.getSearchResult();
		ExtentLogger.pass(TestListener.testNode.get(),"Registration Completed");
		ExtentLogger.addScreenshot(TestListener.testNode.get(),registrationPage.captureScreenshot(Thread.currentThread()
				.getStackTrace()[1]
				.getMethodName()));
		// assertTrue(false);
	}

	@Test(priority = 2,description = "TC02 Registratioin without mandatory data")
	public void registrationwithoutMandatoryDataTest() throws Throwable {

		//ExtentTest testnode = ExtentTestManager.getTest().createNode("TC02 Registratioin without mandatory data");
		//testnode.assignCategory("Tc01_Registration_Valid_Data");

		RegistrationPage registrationPage = new RegistrationPage(page);
		registrationPage.navigateTo(ConfigReader.getProperty("base.url"));
		List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));
		ExtentLogger.info(TestListener.testNode.get(),"Navigated to Regstration page");
		registrationPage.registarionwithoutMandatoryData(dbData.get(0).get("First_Name"), dbData.get(0).get("Last_Name"), dbData.get(0).get("Address"), dbData.get(0).get("city"), dbData.get(0).get("state"), dbData.get(0).get("zip_code"),dbData.get(0).get("phone_number"),dbData.get(0).get("ssn"),dbData.get(0).get("password"));
//        String result = registrationPage.getSearchResult();
		ExtentLogger.pass(TestListener.testNode.get(),"Registration failed due to invalid data");
		ExtentLogger.addScreenshot(TestListener.testNode.get(),registrationPage.captureScreenshot(Thread.currentThread()
				.getStackTrace()[1]
				.getMethodName()));

	}

	@Test(priority = 3,description = "TC03 Registratioin with null data")
	public void registrationwithNullDataTest() throws Throwable {
		//ExtentTest testnode = ExtentTestManager.getTest().createNode("TC03 Registratioin with null data");
		//testnode.assignCategory("Tc01_Registration_Valid_Data");

		RegistrationPage registrationPage = new RegistrationPage(page);
		registrationPage.navigateTo(ConfigReader.getProperty("base.url"));
		List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));
		ExtentLogger.info(TestListener.testNode.get(),"Navigated to Regstration page");
		registrationPage.registarionWithNullData();
//        String result = registrationPage.getSearchResult();
		ExtentLogger.pass(TestListener.testNode.get(),"Registration failed due to null value");
		ExtentLogger.addScreenshot(TestListener.testNode.get(),registrationPage.captureScreenshot(Thread.currentThread()
				.getStackTrace()[1]
				.getMethodName()));

	}
	}
