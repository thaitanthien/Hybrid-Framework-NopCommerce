package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.MyAccountSidebarPageUI;

public class MyAccountSideBarPageObject extends BasePage {
    WebDriver driver;

    public MyAccountSideBarPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AddressPageObject openAddressPage() {
        waitForElementClickable(driver, MyAccountSidebarPageUI.ADDRESSES_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.ADDRESSES_LINK_TEXT);
        return PageGeneratorManager.getAddressPage(driver);
    }

    public OrderPageObject openOrdersPage() {
        waitForElementClickable(driver, MyAccountSidebarPageUI.ORDER_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.ORDER_LINK_TEXT);
        return PageGeneratorManager.getOrderPageObject(driver);
    }

    public CustomerPageObject openCustomerPage() {
        waitForElementClickable(driver, MyAccountSidebarPageUI.CUSTOMER_INFO_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.CUSTOMER_INFO_LINK_TEXT);
        return PageGeneratorManager.getCustomerPage(driver);
    }

    public RewardPointPageObject openRewardPointPage() {
        waitForElementClickable(driver, MyAccountSidebarPageUI.REWARD_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.REWARD_LINK_TEXT);
        return PageGeneratorManager.getRewardPointPage(driver);
    }

    public MyAccountSideBarPageObject openDynamicSideBarPage(String pageName) {
        waitForElementClickable(driver, String.format(MyAccountSidebarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName));
        clickToElement(driver, String.format(MyAccountSidebarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName));
        switch (pageName) {
            case "Addresses":
                return PageGeneratorManager.getAddressPage(driver);
            case "Orders":
                return PageGeneratorManager.getOrderPageObject(driver);
            case "Customer info":
                return PageGeneratorManager.getCustomerPage(driver);
            case "Reward points":
                return PageGeneratorManager.getRewardPointPage(driver);
            default:
                new RuntimeException("Sidebar page is incorrect.");
                return null;
        }
    }
}
