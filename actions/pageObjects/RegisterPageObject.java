package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {

    WebDriver driverPageObj;

    public RegisterPageObject(WebDriver driverPageObj) {
        this.driverPageObj = driverPageObj;
    }

    public String getFirstNameErrorMsgText() {
        waitForElementVisible(driverPageObj, RegisterPageUI.FIRST_NAME_ERROR_MSG);
        return getElementText(driverPageObj, RegisterPageUI.FIRST_NAME_ERROR_MSG);
    }

    public String getLastNameErrorMsgText() {
        waitForElementVisible(driverPageObj, RegisterPageUI.LAST_NAME_ERROR_MSG);
        return getElementText(driverPageObj, RegisterPageUI.LAST_NAME_ERROR_MSG);
    }

    public String getEmailErrorMsgText() {
        waitForElementVisible(driverPageObj, RegisterPageUI.EMAIL_ERROR_MSG);
        return getElementText(driverPageObj, RegisterPageUI.EMAIL_ERROR_MSG);
    }

    public String getPasswordErrorMsgText() {
        waitForElementVisible(driverPageObj, RegisterPageUI.PASSWORD_ERROR_MSG);
        return getElementText(driverPageObj, RegisterPageUI.PASSWORD_ERROR_MSG);
    }

    public String getConfirmPasswordErrorMsgText() {
        waitForElementVisible(driverPageObj, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driverPageObj, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
    }

    public String getRegisterSuccessMsgText() {
        waitForElementVisible(driverPageObj, RegisterPageUI.REGISTRATION_COMPLETED_MSG);
        return getElementText(driverPageObj, RegisterPageUI.REGISTRATION_COMPLETED_MSG);
    }

    public HomePageObject clickTopNopCommerceLogo() {
        waitForElementClickable(driverPageObj, RegisterPageUI.NOP_COMMERCE_LOGO);
        clickToElement(driverPageObj, RegisterPageUI.NOP_COMMERCE_LOGO);
        return PageGeneratorManager.getHomePage(driverPageObj);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driverPageObj, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driverPageObj, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driverPageObj, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driverPageObj, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driverPageObj, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public void clickRegisterBtn() {
        waitForElementClickable(driverPageObj, RegisterPageUI.REGISTER_BTN);
        clickToElement(driverPageObj, RegisterPageUI.REGISTER_BTN);
    }
}
