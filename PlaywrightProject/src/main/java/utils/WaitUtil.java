package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class WaitUtil {

    public static void waitForElement(Page page, String selector, int timeoutInSeconds) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setTimeout(timeoutInSeconds * 1000));
                //.setState(WaitUntilState.VISIBLE));
    }
}
