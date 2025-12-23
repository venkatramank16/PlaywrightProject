package base;

import com.microsoft.playwright.Page;
import utils.BrowserUtils;
import utils.ScreenRecordUtil;
import utils.WaitUtil;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void clickElement(String selector) {
        WaitUtil.waitForElement(page, selector, 30);
        BrowserUtils.click(page, selector);
    }

    public void typeText(String selector, String text) {
        WaitUtil.waitForElement(page, selector, 30);
        BrowserUtils.type(page, selector, text);
    }

    public String getElementText(String selector) {
        WaitUtil.waitForElement(page, selector, 30);
        return BrowserUtils.getText(page, selector);
    }

    public void navigateTo(String url) {
        BrowserUtils.navigate(page, url);
    }

    public String captureScreenshot(String name) {
        return ScreenRecordUtil.captureScreenshot(page, name);
    }
    
    public void selectElement(String selector, String text) {
        WaitUtil.waitForElement(page, selector, 30);
        page.locator(selector).selectOption(text);
    }
}
