package pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import utility.BasePage;

public class LoginPage extends BasePage {
	
	private Page page;
	private ExtentTest extentTest;

    private final String usernameInput = "#email";
    private final String passwordInput = "#password";
    private final String loginButton = "//button[text()='Login']";

    public LoginPage(Page page) {
        super(page);
    }

    public void enterUsername(String username) {
        typeText(usernameInput, username);
    }

    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    public void clickLogin() {
        clickElement(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
