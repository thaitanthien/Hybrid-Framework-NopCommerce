package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.HomePageUI;

public class HomePageObject extends BasePage {

    WebDriver driverPageObject;

    public HomePageObject(WebDriver driverPageObject) {
        this.driverPageObject = driverPageObject;
    }

    @Step("Click to Register link")
    public RegisterPageObject clickRegisterLink() {
        waitForElementClickable(driverPageObject, HomePageUI.REGISTER_LINK);
        clickToElement(driverPageObject, HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driverPageObject);
    }

    @Step("Click to Login link")
    public UserLoginPageObject clickLoginLink() {
        waitForElementClickable(driverPageObject, HomePageUI.LOGIN_LINK);
        clickToElement(driverPageObject, HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driverPageObject);
    }

    @Step("Click to Account link")
    public CustomerPageObject clickAccountLink() {
        waitForElementClickable(driverPageObject, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driverPageObject, HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getCustomerPage(driverPageObject);
    }

    @Step("Click to Logout link")
    public void clickToLogoutLink() {
        waitForElementClickable(driverPageObject, HomePageUI.LOGOUT_LINK);
        clickToElement(driverPageObject, HomePageUI.LOGOUT_LINK);
    }

    @Step("Verify Register link is displayed")
    public boolean isRegisterLinkDisplayed() {
        waitForElementVisible(driverPageObject, HomePageUI.REGISTER_LINK);
        return isElementDisplayed(driverPageObject, HomePageUI.REGISTER_LINK);
    }
}
