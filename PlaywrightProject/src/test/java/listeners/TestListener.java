package listeners;

import base.PlaywrightFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import config.ConfigReader;

import com.microsoft.playwright.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentLogger;
import reporting.ExtentTestManager;
import utils.ScreenRecordUtil;

import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {
   // private static ExtentReports extent = ExtentManager.getExtentReport();
    private static Map<String, ExtentTest> scenarioMap = new HashMap<>();
    public static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {

        String scenarioName;

// Read custom annotation
        Scenario scenarioAnnotation;
       try {
            scenarioAnnotation =
                   result.getTestClass()
                           .getRealClass()
                           .getAnnotation(Scenario.class);
       }catch (Exception e){
           scenarioAnnotation=null;
       }
        if (scenarioAnnotation != null) {
            scenarioName = scenarioAnnotation.value();
        } else {
            // fallback
            scenarioName = result.getTestClass()
                    .getRealClass()
                    .getSimpleName();
        }

        // Test name from @Test(description)
        String testName = result.getMethod().getDescription();

        // Fallback if description is missing
        if (testName == null || testName.isEmpty()) {
            testName = result.getMethod().getMethodName();
        }
        ExtentTest scenario = scenarioMap.get(scenarioName);
        if (scenario == null) {
             ExtentTestManager.startTest(scenarioName);
            //ExtentTestManager.getTest().assignCategory(scenarioName);
            scenarioMap.put(scenarioName, ExtentTestManager.getTest());
        }
       // scenario = scenarioMap.get(scenarioName);
        ExtentTest node = ExtentTestManager.getTest().createNode(testName);
        testNode.set(node);
    }
       // ExtentTestManager.startTest(result.getMethod().getDescription());
        //ExtentLogger.info(ExtentTestManager.getTest(),"Test Started: " + result.getMethod().getDescription());


    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass( testNode.get(),"Test Passed: " + result.getMethod().getDescription());
        //PlaywrightFactory.saveVideo(result.getTestName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

       ExtentLogger.fail( testNode.get(),"Test Failed: " + result.getMethod().getDescription());

       // try {
        	//String testName = result.getMethod().getMethodName();
           // String videoPath = PlaywrightFactory.saveVideo(testName);

           // if (videoPath != null) {
               // ExtentLogger.fail("Video Recorded: " + videoPath);
               // ExtentTestManager.getTest().addScreenCaptureFromPath(videoPath);
            //}
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.info( testNode.get(),"Test Skipped: " + result.getMethod().getDescription());
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.endTest();
    }
}
