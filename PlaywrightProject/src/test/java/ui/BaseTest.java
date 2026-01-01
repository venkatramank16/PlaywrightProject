package ui;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

import base.PlaywrightFactory;
import config.ConfigReader;


import java.io.File;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentTestManager;

public class BaseTest {

    protected Page page;

    @BeforeSuite
    public void beforeSuite() {
    	File f=new File(System.getProperty("user.dir")+"\\reports\\videos");
    	for(File file: f.listFiles()) 
    	    if (!file.isDirectory()) 
    	        file.delete();
    	
    }
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, Method method, ITestResult result) {

        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        int retryCount = result.getAttribute("retryCount") == null
                ? 0
                : (int) result.getAttribute("retryCount");

        PlaywrightFactory.initBrowser(browser, headless,  retryCount);

        page = PlaywrightFactory.getPage();

      
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
      
        
       page.close();
        PlaywrightFactory.saveVideo(result.getName());
        
    }
    
    @AfterSuite
    public void endSuite() {
    	  ExtentTestManager.endTest();
    	 PlaywrightFactory.closeBrowser();
    }
}
