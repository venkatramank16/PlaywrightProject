package reporting;

import java.nio.file.Paths;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.ConfigReader;
import config.PropertyUtils;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Demo Playwright + Rest Assured Automation");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            String applicationURL = "<a href=\"" + ConfigReader.getProperty("base.url")
            + "\" target=\"_blank\">Demo Application</a>";
            extent.setSystemInfo("Application", applicationURL);

            extent.setSystemInfo("OS", System.getProperties().getProperty("os.name"));
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extent.setSystemInfo("Framework", "Playwright + TestNG + Rest-Assured");
            extent.setSystemInfo("Author", "Venkatraman K");
            String filePath = Paths.get(System.getProperty("user.dir")+"/reports/videos").toAbsolutePath()
                    .toString();
            String recordedVideoFilePath = "<a href=\"" + filePath
                    + "\" target=\"_blank\">Video</a>";
            extent.setSystemInfo("Execution Recorded Video", recordedVideoFilePath);
        }
        return extent;
    }
}
