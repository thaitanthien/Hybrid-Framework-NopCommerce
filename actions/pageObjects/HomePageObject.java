package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {

    WebDriver driverPageObject;

    public HomePageObject(WebDriver driverPageObject) {
        this.driverPageObject = driverPageObject;
    }

    public void clickRegisterLink() {
        waitForElementClickable(driverPageObject, HomePageUI.REGISTER_LINK);
        clickToElement(driverPageObject, HomePageUI.REGISTER_LINK);
    }

    public void clickLoginLink() {
        waitForElementClickable(driverPageObject, HomePageUI.LOGIN_LINK);
        clickToElement(driverPageObject, HomePageUI.LOGIN_LINK);
    }

    public void clickAccountLink() {
        waitForElementClickable(driverPageObject, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driverPageObject, HomePageUI.MY_ACCOUNT_LINK);
    }
}
