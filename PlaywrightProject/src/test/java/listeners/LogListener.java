package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Simple listener for console logging of tests
 */
public class LogListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[START] " + result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] " + result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] " + result.getMethod().getDescription() + " - " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED] " + result.getMethod().getDescription());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("[TEST SUITE START] " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[TEST SUITE END] " + context.getName());
    }
}
