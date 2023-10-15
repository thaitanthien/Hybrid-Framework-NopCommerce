package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.RegisterPageUI;

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

    @Step("Enter to First name textbox with value {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driverPageObj, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to Last name textbox with value {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driverPageObj, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Enter to Email address textbox with value {0}")
    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driverPageObj, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Enter to Password textbox with value {0}")
    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driverPageObj, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Enter to Confirm password textbox with value {0}")
    public void enterToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driverPageObj, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driverPageObj, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    @Step("Click Register link")
    public void clickRegisterBtn() {
        waitForElementClickable(driverPageObj, RegisterPageUI.REGISTER_BTN);
        clickToElement(driverPageObj, RegisterPageUI.REGISTER_BTN);
    }
}
