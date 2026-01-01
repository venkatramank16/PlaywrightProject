package pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import base.BasePage;
import reporting.ExtentLogger;

public class LoginPage extends BasePage {

	

	private final String usernameInput = "//input[@name='username']";
	private final String passwordInput = "//input[@name='password']";
	private final String loginButton = "//input[@value='Log In']";

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
		//ExtentLogger.pass(testnode,"Username entered as: "+username);
		enterPassword(password);
		//ExtentLogger.pass("Password entered as: "+password);
		clickLogin();
	}
}
