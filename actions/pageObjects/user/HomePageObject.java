package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.HomePageUI;

public class HomePageObject extends BasePage {

    WebDriver driverPageObject;

    public HomePageObject(WebDriver driverPageObject) {
        this.driverPageObject = driverPageObject;
    }

    public RegisterPageObject clickRegisterLink() {
        waitForElementClickable(driverPageObject, HomePageUI.REGISTER_LINK);
        clickToElement(driverPageObject, HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driverPageObject);
    }

    public UserLoginPageObject clickLoginLink() {
        waitForElementClickable(driverPageObject, HomePageUI.LOGIN_LINK);
        clickToElement(driverPageObject, HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driverPageObject);
    }

    public CustomerPageObject clickAccountLink() {
        waitForElementClickable(driverPageObject, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driverPageObject, HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getCustomerPage(driverPageObject);
    }

    public void clickToLogoutLink() {
        waitForElementClickable(driverPageObject, HomePageUI.LOGOUT_LINK);
        clickToElement(driverPageObject, HomePageUI.LOGOUT_LINK);
    }
}
