package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {

    WebDriver driverPageObject;


    public LoginPageObject(WebDriver driverPageObject) {
        this.driverPageObject = driverPageObject;
    }

    public void enterEmailTextbox(String email) {
        waitForElementVisible(driverPageObject, LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driverPageObject, LoginPageUI.EMAIL_TEXTBOX, email);
    }
    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driverPageObject, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driverPageObject, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public HomePageObject clickLoginBtn() {
        waitForElementClickable(driverPageObject, LoginPageUI.LOGIN_BTN);
        clickToElement(driverPageObject, LoginPageUI.LOGIN_BTN);
        return PageGeneratorManager.getHomePage(driverPageObject);
    }

}
