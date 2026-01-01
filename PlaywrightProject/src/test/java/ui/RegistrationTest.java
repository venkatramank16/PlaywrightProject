package ui;

import base.RetryAnalyzer;
import base.TestDataManager;
import config.ConfigReader;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.RegistrationPage;
import reporting.ExtentLogger;
import reporting.ExtentTestManager;

public class RegistrationTest extends BaseTest {

	@BeforeClass
	public void setupBeforeClass() {
		  ExtentTestManager.startTest("TS_02 Registration Functionality Validation");
	}
	
	@Test()
	public void registrationwithValidDataTest() throws Throwable {
		
		ExtentTest testnode = ExtentTestManager.getTest().createNode("TC01 Registratioin with valid data");
    	testnode.assignCategory("Tc01_Registration_Valid_Data");

		RegistrationPage registrationPage = new RegistrationPage(page);
		registrationPage.navigateTo(ConfigReader.getProperty("base.url"));
List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));
		ExtentLogger.info(testnode,"Navigated to Regstration page");
		registrationPage.registarion(dbData.get(0).get("First_Name"), dbData.get(0).get("Last_Name"), dbData.get(0).get("Address"), dbData.get(0).get("city"), dbData.get(0).get("state"), dbData.get(0).get("zip_code"),dbData.get(0).get("phone_number"),dbData.get(0).get("ssn"),dbData.get(0).get("password"));
//        String result = registrationPage.getSearchResult();
		ExtentLogger.pass(testnode,"Registration Completed");
		ExtentLogger.addScreenshot(testnode,registrationPage.captureScreenshot("RegistrationPage"));
		// assertTrue(false);
	}

	@Test()
	public void registrationwithoutMandatoryDataTest() throws Throwable {

		ExtentTest testnode = ExtentTestManager.getTest().createNode("TC01 Registratioin with valid data");
		testnode.assignCategory("Tc01_Registration_Valid_Data");

		RegistrationPage registrationPage = new RegistrationPage(page);
		registrationPage.navigateTo(ConfigReader.getProperty("base.url"));
		List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));
		ExtentLogger.info(testnode,"Navigated to Regstration page");
		registrationPage.registarionwithoutMandatoryData(dbData.get(0).get("First_Name"), dbData.get(0).get("Last_Name"), dbData.get(0).get("Address"), dbData.get(0).get("city"), dbData.get(0).get("state"), dbData.get(0).get("zip_code"),dbData.get(0).get("phone_number"),dbData.get(0).get("ssn"),dbData.get(0).get("password"));
//        String result = registrationPage.getSearchResult();
		ExtentLogger.pass(testnode,"Registration failed due to invalid data");
		ExtentLogger.addScreenshot(testnode,registrationPage.captureScreenshot("RegistrationPage"));

	}

	@Test()
	public void registrationwithNullDataTest() throws Throwable {
		ExtentTest testnode = ExtentTestManager.getTest().createNode("TC01 Registratioin with valid data");
		testnode.assignCategory("Tc01_Registration_Valid_Data");

		RegistrationPage registrationPage = new RegistrationPage(page);
		registrationPage.navigateTo(ConfigReader.getProperty("base.url"));
		List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));
		ExtentLogger.info(testnode,"Navigated to Regstration page");
		registrationPage.registarionWithNullData();
//        String result = registrationPage.getSearchResult();
		ExtentLogger.pass(testnode,"Registration failed due to null value");
		ExtentLogger.addScreenshot(testnode,registrationPage.captureScreenshot("RegistrationPage"));

	}
	}
