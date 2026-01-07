package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import utils.UIActions;

public class AccountCreationPage  extends BasePage {
    UIActions uiActions;
    public AccountCreationPage(Page page) {
        super(page);
        uiActions=new UIActions(page);
    }

    private String openNewAccountLink="//a[text()='Open New Account']";
private String accountTypeDrop="//select[@id='type']";
private String existingAccountSelectionDrop="//select[@id='fromAccountId']";
private String openNewAccButton="//input[@value='Open New Account']";
private String verifySuccessMessageText="//p[text()='Congratulations, your account is now open.']";
private String newAccountNoText="//a[@id='newAccountId']";
public void navigateToNewAccPage(){
    clickElement(openNewAccountLink);
}
    public void savingAccount(){
selectElement(accountTypeDrop,"SAVINGS");
    }

    public void checkingAccount(){
        selectElement(accountTypeDrop,"CHECKING");
    }

    public void clickOpenNew(){
        clickElement(openNewAccButton);
    }

   public String getAccountNumber(){

       return uiActions.getText(newAccountNoText);
   }

  public void  verifySuccessMessage(){
uiActions.isPresent(verifySuccessMessageText);

  }
}
