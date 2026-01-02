package utility;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Video;

import config.PropertyUtils;

public class PlaywrightFactory {

    private static final ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();
    private static final ThreadLocal<String> videoPathThread = new ThreadLocal<>();
    private static final ThreadLocal<Video> videoThread = new ThreadLocal<>();
    private static Playwright playwright;
    private static Logger log = LogManager.getLogger();
    private PropertyUtils testProperties;

    public PlaywrightFactory(PropertyUtils testProperties) {
        this.testProperties = testProperties;
        if (playwright == null) {
            playwright = Playwright.create();
        }
    }
    
    private Browser initBrowser() throws IllegalArgumentException {
        String browserName = testProperties.getProperty("browser");
        boolean headless = Boolean.parseBoolean(testProperties.getProperty("headless"));
        LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);
        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "chromium":
                browserType = playwright.chromium();
                break;
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "safari":
                browserType = playwright.webkit();
                break;
            case "chrome":
                browserType = playwright.chromium();
                launchOptions.setChannel("chrome");
                break;
            case "edge":
                browserType = playwright.chromium();
                launchOptions.setChannel("msedge");
                break;
            default:
                String message = "Browser Name '" + browserName + "' specified in Invalid.";
                message += " Please specify one of the supported browsers [chromium, firefox, safari, chrome, edge].";
                log.debug(message);
                throw new IllegalArgumentException(message);
        }
        log.info("Browser Selected for Test Execution '{}' with headless mode as '{}'", browserName, headless);
        Browser browser = browserType.launch(launchOptions);
        browserThread.set(browser);
        return browser;
        
    }
    
    
    private BrowserContext getBrowserContext() {
        BrowserContext browserContext;
        Browser browser = initBrowser();
        NewContextOptions newContextOptions = new Browser.NewContextOptions();

        if (Boolean.parseBoolean(testProperties.getProperty("video.record"))) {
            Path path = Paths.get(testProperties.getProperty("video.dir"));
            newContextOptions.setRecordVideoDir(path);
            log.info("Browser Context - Video Recording is enabled at location '{}'", path.toAbsolutePath());
        }

        int viewPortHeight = Integer.parseInt(testProperties.getProperty("viewPortHeight"));
        int viewPortWidth = Integer.parseInt(testProperties.getProperty("viewPortWidth"));
        newContextOptions.setViewportSize(viewPortWidth, viewPortHeight);
        log.info("Browser Context - Viewport Width '{}' and Height '{}'", viewPortWidth, viewPortHeight);

        

        browserContext = (browser.newContext(newContextOptions));

        if (Boolean.parseBoolean(testProperties.getProperty("enableTracing"))) {
            browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
            log.info("Browser Context - Tracing is enabled with Screenshots and Snapshots");
        }
        
        contextThread.set(browserContext);
        return browserContext;
    }
    
    public Page createPage() {
        Page page = null;
        try {
            page = (initBrowser().newPage());
        } catch (Exception e) {
            log.error("Unable to create Page : ", e);
        }
        return page;
    }

    public static Page getPage() {
        return pageThread.get();
    }

    public static String getVideoPath() {
        return videoPathThread.get();
    }

    public static BrowserContext getContext() {
        return contextThread.get();
    }

    public static Browser getBrowser() {
        return browserThread.get();
    }
    public static String takeScreenshot(Page page) {
        String path = System.getProperty("user.dir") + "/test-results/screenshots/" + System.currentTimeMillis()
                + ".png";

        byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        log.debug("Screenshot is taken and saved at the location  {}", path);
        return base64Path;
    }
    public static void closeBrowser() {
        try {
            if (pageThread.get() != null) pageThread.get().close();
            if (contextThread.get() != null) contextThread.get().close();
            if (browserThread.get() != null) browserThread.get().close();
            if (playwright != null) playwright.close();
        } catch (Exception ignored) {}

        pageThread.remove();
        contextThread.remove();
        browserThread.remove();
        videoPathThread.remove();
    }
}
