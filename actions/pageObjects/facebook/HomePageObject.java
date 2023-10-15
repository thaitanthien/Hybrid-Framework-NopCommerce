package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }


    public void clickToCreateNewAccountBtn() {
        waitForElementClickable(driver, HomePageUI.CREATE_NEW_ACCOUNT_BTN);
        clickToElement(driver, HomePageUI.CREATE_NEW_ACCOUNT_BTN);
    }

    public boolean isFirstNameTextboxDisplayed() {
//        waitForElementVisible(driver, HomePageUI.FIRSTNAME_INPUT);
        return isElementDisplayed(driver, HomePageUI.FIRSTNAME_INPUT);
    }

    public boolean isSurNameTextboxDisplayed() {
        return isElementDisplayed(driver, HomePageUI.SURNAME_INPUT);

    }

    public boolean isEmailTextboxDisplayed() {
        return isElementDisplayed(driver, HomePageUI.EMAIL_INPUT);
    }

    public boolean isConfirmEmailTextboxDisplayed() {
        return isElementDisplayed(driver, HomePageUI.CONFIRM_EMAIL_INPUT);
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, HomePageUI.EMAIL_INPUT);

        sendKeyToElement(driver, HomePageUI.EMAIL_INPUT, email);
        sleepInSecond(2);
    }

    public void clickCloseSignupPopup() {
        waitForElementClickable(driver, HomePageUI.CLOSE_SIGN_UP_POPUP);
        clickToElement(driver, HomePageUI.CLOSE_SIGN_UP_POPUP);
        sleepInSecond(2);
    }

    public boolean isFirstNameTextboxUnDisplayed() {
        return isElementUndisplayed(driver, HomePageUI.FIRSTNAME_INPUT);
    }

    public boolean isSurNameTextboxUnDisplayed() {
        return isElementUndisplayed(driver, HomePageUI.SURNAME_INPUT);
    }

    public boolean isEmailTextboxUnDisplayed() {
        return isElementUndisplayed(driver, HomePageUI.EMAIL_INPUT);
    }

}
