package pages;

import com.microsoft.playwright.Page;

import base.BasePage;

public class RegistrationPage extends BasePage {

	private final String registrationLink = "//span[text()='Registration']";
	private final String nameInput = "#name";
	private final String countryDrop = "#country";
	private final String accountDrop = "#account";
	private final String emailInput = "#email";
	private final String passwordInput = "#password";
	private final String confirmPasswordInput = "#confirm_password";
	private final String signupButton = "//button[text()='Signup']";

	public RegistrationPage(Page page) {
		super(page);
	}

	public void registarion(String name, String country, String account, String email, String password,
			String confirm) {
		clickElement(registrationLink);
		typeText(nameInput, name);
		selectElement(countryDrop, country);
		selectElement(accountDrop, account);
		typeText(emailInput, email);
		typeText(passwordInput, password);
		typeText(confirmPasswordInput, confirm);
		clickElement(signupButton);
	}

	public String getSearchResult() {
		return getElementText("#searchResults");
	}
}
