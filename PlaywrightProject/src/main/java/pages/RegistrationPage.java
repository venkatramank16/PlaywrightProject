package pages;

import com.microsoft.playwright.Page;

import base.BasePage;

public class RegistrationPage extends BasePage {

	private final String registrationLink = "//a[text()='Register']";
	private final String firstnameInput = "//input[@id='customer.firstName']";
	private final String lastnameInput = "//input[@id='customer.lastName']";
	private final String addressInput = "//input[@id='customer.address.street']";
	private final String cityInput = "//input[@id='customer.address.city']";
	private final String stateInput = "//input[@id='customer.address.state']";
	private final String zipCodeInput = "//input[@id='customer.address.zipCode']";
	private final String phoneNumberInput = "//input[@id='customer.phoneNumber']";
	private final String ssnInput = "//input[@id='customer.ssn']";

	private final String usernameInput = "//input[@id='customer.username']";
	private final String passwordInput = "//input[@id='customer.password']";
	private final String confirmPasswordInput = "//input[@id='repeatedPassword']";
	private final String registerButton = "//input[@value='Register']";


	public RegistrationPage(Page page) {
		super(page);
	}

	public void registarion(String firstname, String lastname, String address, String city, String state, String zip, String phone, String ssn,String password) {
		clickElement(registrationLink);
		typeText(firstnameInput, firstname);
		typeText(lastnameInput, lastname);
		typeText(addressInput, address);
		typeText(cityInput, city);
		typeText(stateInput, state);
		typeText(zipCodeInput, zip);
		typeText(phoneNumberInput, phone);
		typeText(ssnInput, ssn);
		typeText(usernameInput, firstname);
		typeText(passwordInput, password);
		typeText(confirmPasswordInput, password);
		clickElement(registerButton);
	}

	public void registarionwithoutMandatoryData(String firstname, String lastname, String address, String city, String state, String zip, String phone, String ssn,String password) {
		clickElement(registrationLink);
		//typeText(firstnameInput, firstname);
		//typeText(lastnameInput, lastname);
		typeText(addressInput, address);
		typeText(cityInput, city);
		typeText(stateInput, state);
		typeText(phoneNumberInput, phone);
		typeText(ssnInput, ssn);
		typeText(usernameInput, firstname);
		typeText(passwordInput, password);
		typeText(confirmPasswordInput, password);
		clickElement(registerButton);
	}
	public void registarionWithNullData() {
		clickElement(registrationLink);

		clickElement(registerButton);
	}

	public String getSearchResult() {
		return getElementText("#searchResults");
	}


}
