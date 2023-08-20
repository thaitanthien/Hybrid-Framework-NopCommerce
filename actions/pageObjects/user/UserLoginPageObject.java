package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {

    WebDriver driverPageObject;


    public UserLoginPageObject(WebDriver driverPageObject) {
        this.driverPageObject = driverPageObject;
    }

    public void enterEmailTextbox(String email) {
        waitForElementVisible(driverPageObject, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driverPageObject, UserLoginPageUI.EMAIL_TEXTBOX, email);
    }
    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driverPageObject, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driverPageObject, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public HomePageObject clickLoginBtn() {
        waitForElementClickable(driverPageObject, UserLoginPageUI.LOGIN_BTN);
        clickToElement(driverPageObject, UserLoginPageUI.LOGIN_BTN);
        return PageGeneratorManager.getHomePage(driverPageObject);
    }

    public HomePageObject loginToUser(String emailAddress, String password) {
        enterEmailTextbox(emailAddress);
        enterPasswordTextbox(password);
        return clickLoginBtn();
    }

}
