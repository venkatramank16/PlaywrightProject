package ui;


import java.io.File;
import java.lang.reflect.Method;
import static utility.PlaywrightFactory.takeScreenshot;
import static reporting.ExtentTestManager.extentLogWithScreenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

import config.PropertyUtils;
import pages.LoginPage;
import pages.RegistrationPage;
import reporting.ExtentTestManager;
import utility.PlaywrightFactory;

public class BaseTest {

	protected Page page;
	protected SoftAssert softAssert = new SoftAssert();
	protected ExtentTest extentTest, testNode;
	protected RegistrationPage registrationPage;
	protected LoginPage loginPage;
	protected static ExtentReports reporter;
	protected static PropertyUtils testProperties;
	private static Logger log;

	@BeforeSuite
	public void beforeSuite() throws Throwable {
		File f = new File(System.getProperty("user.dir") + "\\reports\\videos");
		if (f.exists() && !deleteDirectory(f)) {
			throw new Exception("Exception occurred while deleting test-results directory");
		}
		log = LogManager.getLogger();
		testProperties = new PropertyUtils();
	//	testProperties.updateTestProperties();
		reporter = ExtentTestManager.getExtentReporter(testProperties);
		

	}

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, Method method) {

		PlaywrightFactory pf = new PlaywrightFactory(testProperties);
		page = pf.createPage();
		page.navigate(testProperties.getProperty("base.url"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		if (!result.isSuccess())
			extentLogWithScreenshot(testNode, Status.WARNING, "The test is not Passed. Please refer the previous step.",
					takeScreenshot(page));
		page.context().browser().close();
		reporter.flush();
		PlaywrightFactory.closeBrowser();

	}
	@AfterSuite
	public void teardownAfterTestSuite() {
		try {
			
			softAssert.assertAll();
			reporter.flush();
		} catch (Exception e) {
			log.error("Error in AfterSuite Method ", e);
		}
	}
	private boolean deleteDirectory(File f) {
    	for(File file: f.listFiles()) 
    	    if (!file.isDirectory()) 
    	        file.delete();
		return true;
	}
}
