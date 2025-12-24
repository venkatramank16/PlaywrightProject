package reporting;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ExtentLogger {

    public static void pass(ExtentTest extentTest, String message) {
    	extentTest.pass(message);
    }

    public static void fail(ExtentTest extentTest,String message) {
    	extentTest.fail(message);
    }

    public static void info(ExtentTest extentTest,String message) {
        extentTest.info(message);

    }
    public static void addScreenshot(ExtentTest extentTest,String path) {
        try {
        	extentTest.info("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
