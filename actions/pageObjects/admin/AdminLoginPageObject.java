package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {

    WebDriver driverPageObject;


    public AdminLoginPageObject(WebDriver driverPageObject) {
        this.driverPageObject = driverPageObject;
    }

    public void enterEmailTextbox(String email) {
        waitForElementVisible(driverPageObject, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driverPageObject, AdminLoginPageUI.EMAIL_TEXTBOX, email);
    }
    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driverPageObject, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driverPageObject, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public AdminDashboardPageObject clickLoginBtn() {
        waitForElementClickable(driverPageObject, AdminLoginPageUI.LOGIN_BTN);
        clickToElement(driverPageObject, AdminLoginPageUI.LOGIN_BTN);
        return PageGeneratorManager.getAdminDashboardPage(driverPageObject);
    }

    public AdminDashboardPageObject loginToAdmin(String emailAddress, String password) {
//        enterEmailTextbox(emailAddress);
//        enterPasswordTextbox(password);
        return clickLoginBtn();
    }

}
