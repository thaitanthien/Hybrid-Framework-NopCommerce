package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageUIs.user.CustomerPageUI;

public class CustomerPageObject extends MyAccountSideBarPageObject {
    WebDriver driverPageObj;

    public CustomerPageObject(WebDriver driverPageObj) {
        super(driverPageObj);
        this.driverPageObj = driverPageObj;
    }

    public String getFirstNameTextboxAttributeValue() {
        waitForElementVisible(driverPageObj, CustomerPageUI.FIRST_NAME_TEXTBOX);
        return getAttributeValue(driverPageObj, CustomerPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxAttributeValue() {
        waitForElementVisible(driverPageObj, CustomerPageUI.LAST_NAME_TEXTBOX);
        return getAttributeValue(driverPageObj, CustomerPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailAddressTextboxAttributeValue() {
        waitForElementVisible(driverPageObj, CustomerPageUI.EMAIL_TEXTBOX);
        return getAttributeValue(driverPageObj, CustomerPageUI.EMAIL_TEXTBOX, "value");
    }
}
