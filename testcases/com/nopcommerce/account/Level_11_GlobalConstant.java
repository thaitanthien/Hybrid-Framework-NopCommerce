package com.nopcommerce.account;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

public class Level_11_GlobalConstant extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
    private final String emailAddress = getEmailRandom();

    private final String adminUrl = GlobalConstants.DEV_ADMIN_URL;
    private final String endUserUrl = GlobalConstants.DEV_USER_URL;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName, endUserUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Register_01_UserToAdmin() {
        registerPage = homePage.clickRegisterLink();
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickRegisterBtn();
        Assert.assertEquals(registerPage.getRegisterSuccessMsgText(), "Your registration completed");

        homePage = registerPage.clickTopNopCommerceLogo();
        loginPage = homePage.clickLoginLink();
        homePage = loginPage.loginToUser(emailAddress, "123456");
        homePage.clickToLogoutLink();

        // HomePage (User) -> LoginPage(Admin)
        homePage.openPageUrl(driver, adminUrl);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminDashboardPage = adminLoginPage.loginToAdmin(GlobalConstants.DEV_ADMIN_USERNAME, GlobalConstants.DEV_ADMIN_PASSWORD);
        Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));
    }

    @Test
    public void Register_02_AdminToUser() {
        adminLoginPage = adminDashboardPage.clickLogoutLink();
        adminLoginPage.openPageUrl(driver, adminUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
