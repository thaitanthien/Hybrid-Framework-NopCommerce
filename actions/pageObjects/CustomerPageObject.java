package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage {
    WebDriver driverPageObj;

    public CustomerPageObject(WebDriver driverPageObj) {
        this.driverPageObj = driverPageObj;
    }

    public String getFirstNameTextboxAttributeValue() {
        waitForElementInvisible(driverPageObj, CustomerPageUI.FIRST_NAME_TEXTBOX);
        return getElementText(driverPageObj, CustomerPageUI.FIRST_NAME_TEXTBOX);
    }

    public String getLastNameTextboxAttributeValue() {
        waitForElementInvisible(driverPageObj, CustomerPageUI.LAST_NAME_TEXTBOX);
        return getElementText(driverPageObj, CustomerPageUI.LAST_NAME_TEXTBOX);
    }

    public String getEmailAddressTextboxAttributeValue() {
        waitForElementInvisible(driverPageObj, CustomerPageUI.EMAIL_TEXTBOX);
        return getElementText(driverPageObj, CustomerPageUI.EMAIL_TEXTBOX);
    }
}
