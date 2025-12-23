package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static ExtentReports extent = ExtentManager.getExtentReports();

    public static synchronized void startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);
    }

    public static synchronized ExtentTest getTest() {
        return testThread.get();
    }

    public static synchronized void endTest() {
        extent.flush();
        testThread.remove();
    }
}
