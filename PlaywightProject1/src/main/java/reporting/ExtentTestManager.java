package reporting;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.PropertyUtils;

public class ExtentTestManager {
	private ExtentTestManager() {
        throw new IllegalStateException("Extent Reporter class instantiation is not allowed");
    }
	
	 private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	    private static ExtentReports extent = ExtentManager.getExtentReports();
	
	 public static ExtentReports getExtentReporter(PropertyUtils testProperties) throws IOException {
	        ExtentSparkReporter reporter = new ExtentSparkReporter(testProperties.getProperty("report.path"));
	        reporter.loadXMLConfig("./src/main/resources/extent-report-config.xml");

	        reporter.config().setCss("img.r-img { width: 30%; }");
	        ExtentReports extentReports = new ExtentReports();
	        extentReports.attachReporter(reporter);

	        String applicationURL = "<a href=\"" + testProperties.getProperty("base.url")
	                + "\" target=\"_blank\">Demo Application</a>";
	        extentReports.setSystemInfo("Application", applicationURL);

	        extentReports.setSystemInfo("OS", System.getProperties().getProperty("os.name"));
	        extentReports.setSystemInfo("Browser", testProperties.getProperty("browser"));

	        if (Boolean.getBoolean(testProperties.getProperty("video.record"))) {
	            String filePath = Paths.get(testProperties.getProperty("video.dir")).toAbsolutePath()
	                    .toString();
	            String recordedVideoFilePath = "<a href=\"" + filePath
	                    + "\" target=\"_blank\"Demo Application</a>";
	            extentReports.setSystemInfo("Execution Recorded Video", recordedVideoFilePath);
	        }
	        return extentReports;
	    }
	 public static void extentLog(ExtentTest extentTest, Status status, String message) {
	        extentTest.log(status, message);
	        log(status, message);
	    }
	 public static void extentLogWithScreenshot(ExtentTest extentTest, Status status, String message,
	            String base64Path) {
	        String imageElement = "<br/><img class='r-img' src='data:image/png;base64," + base64Path
	                + "' href='data:image/png;base64," + base64Path + "'data-featherlight='image'>";
	        extentTest.log(status, message + imageElement);
	        log(status, message);
	    }
	 
	 private static void log(Status status, String message) {
	        message = message.replaceAll("\\<.*?\\>", "");
	        Logger log = LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName().split("\\.")[1]+ "." + Thread.currentThread().getStackTrace()[3].getMethodName());
	        Marker marker = MarkerManager.getMarker("ReportLog");
	        switch (status) {
	            case FAIL:
	                log.warn(marker, message);
	                break;
	            case WARNING:
	                log.warn(marker, message);
	                break;
	            case SKIP:
	                log.warn(marker, message);
	                break;
	            case INFO:
	                log.info(marker, message);
	                break;
	            default:
	                log.debug(marker, message);
	                break;
	        }
	    }

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
