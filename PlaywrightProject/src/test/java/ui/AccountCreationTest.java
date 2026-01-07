package ui;

import base.TestDataManager;
import config.ConfigReader;
import listeners.Scenario;
import listeners.TestListener;
import org.testng.annotations.Test;
import pages.AccountCreationPage;
import pages.LoginPage;
import pages.RegistrationPage;
import reporting.ExtentLogger;

import java.util.List;
import java.util.Map;
@Scenario("Account Creation Page")
public class AccountCreationTest extends BaseTest{

    @Test(description = "TC01 Checking Account Creation")
    public void checkingAccountCreationTest() throws Throwable {

        List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));

        String username=dbData.get(0).get("username");
        String password=dbData.get(0).get("password");

        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo(ConfigReader.getProperty("base.url"));

        ExtentLogger.info(TestListener.testNode.get(),"Navigated to login page");
        loginPage.login(username, password);
        AccountCreationPage accountCreationPage = new AccountCreationPage(page);
        accountCreationPage.navigateToNewAccPage();
        ExtentLogger.info(TestListener.testNode.get(),"Navigated to new account page");
        accountCreationPage.checkingAccount();
        Thread.sleep(2000);
        accountCreationPage.clickOpenNew();
        accountCreationPage.verifySuccessMessage();
        ExtentLogger.pass(TestListener.testNode.get(),"Checking account created");
       Thread.sleep(2000);
        ExtentLogger.info(TestListener.testNode.get(),"New Account Number: "+accountCreationPage.getAccountNumber());
        ExtentLogger.addScreenshot(TestListener.testNode.get(),accountCreationPage.captureScreenshot(Thread.currentThread()
                .getStackTrace()[1]
                .getMethodName()));

    }
    @Test(description = "TC02 Saving Account Creation")
    public void SavingAccountCreationTest() throws Throwable {

        List<Map<String, String>> dbData = TestDataManager.getDBData(ConfigReader.getProperty("query"));

        String username=dbData.get(0).get("username");
        String password=dbData.get(0).get("password");

        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo(ConfigReader.getProperty("base.url"));

        ExtentLogger.info(TestListener.testNode.get(),"Navigated to login page");
        loginPage.login(username, password);
        AccountCreationPage accountCreationPage = new AccountCreationPage(page);
        accountCreationPage.navigateToNewAccPage();
        ExtentLogger.info(TestListener.testNode.get(),"Navigated to new account page");
        accountCreationPage.savingAccount();
        Thread.sleep(2000);
        accountCreationPage.clickOpenNew();
        accountCreationPage.verifySuccessMessage();
        ExtentLogger.pass(TestListener.testNode.get(),"Savings account created");
        Thread.sleep(2000);
        ExtentLogger.info(TestListener.testNode.get(),"New Account Number: "+accountCreationPage.getAccountNumber());
        ExtentLogger.addScreenshot(TestListener.testNode.get(),accountCreationPage.captureScreenshot(Thread.currentThread()
                .getStackTrace()[1]
                .getMethodName()));

    }


}
