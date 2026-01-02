package utility;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void clickElement(String selector) {
        WaitUtil.waitForElement(page, selector, 30);
        page.click(selector);
    }

    public void typeText(String selector, String text) {
        WaitUtil.waitForElement(page, selector, 30);
        page.fill( selector, text);
    }

    public String getElementText(String selector) {
        WaitUtil.waitForElement(page, selector, 30);
        return page.textContent( selector);
    }

    public void navigateTo(String url) {
        page.navigate( url);
    }

    public String captureScreenshot(String name) {
        return PlaywrightFactory.takeScreenshot(page);
    }
    
    public void selectElement(String selector, String text) {
        WaitUtil.waitForElement(page, selector, 30);
        page.locator(selector).selectOption(text);
    }
}
