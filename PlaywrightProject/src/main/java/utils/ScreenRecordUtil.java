package utils;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class ScreenRecordUtil {

    public static String captureScreenshot(Page page, String fileName) {
        try {
            String path = System.getProperty("user.dir")+"/reports/screenshots/" + fileName + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
