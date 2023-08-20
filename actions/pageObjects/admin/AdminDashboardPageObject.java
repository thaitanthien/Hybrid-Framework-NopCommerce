package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminDashboardPageUI;
import pageUIs.admin.AdminLoginPageUI;

public class AdminDashboardPageObject extends BasePage {
    WebDriver driver;
    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AdminLoginPageObject clickLogoutLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.LOGOUT_LINK);
        clickToElement(driver, AdminDashboardPageUI.LOGOUT_LINK);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }
}
