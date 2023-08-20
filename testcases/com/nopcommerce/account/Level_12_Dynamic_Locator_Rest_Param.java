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
import pageObjects.user.*;

public class Level_12_Dynamic_Locator_Rest_Param extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private OrderPageObject orderPage;
    private AddressPageObject addressPage;
    private RewardPointPageObject rewardPointPage;
    private final String emailAddress = getEmailRandom();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Register_01_Register_Success() {
        registerPage = homePage.clickRegisterLink();
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickRegisterBtn();
        Assert.assertEquals(registerPage.getRegisterSuccessMsgText(), "Your registration completed");
    }

    @Test
    public void Register_02_Login_Success() {
        homePage = registerPage.clickTopNopCommerceLogo();
        loginPage = homePage.clickLoginLink();
        loginPage.enterEmailTextbox(emailAddress);
        loginPage.enterPasswordTextbox("123456");
        homePage = loginPage.clickLoginBtn();
        customerPage = homePage.clickAccountLink();
        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(), "John");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(), "Kennedy");
        Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(), emailAddress);
    }

    @Test
    public void Register_03_SwitchPage() {
        orderPage = (OrderPageObject) customerPage.openDynamicSideBarPage("Orders");
        addressPage = (AddressPageObject) orderPage.openDynamicSideBarPage("Addresses");
        rewardPointPage = (RewardPointPageObject) addressPage.openDynamicSideBarPage("Reward points");
        orderPage = (OrderPageObject) rewardPointPage.openDynamicSideBarPage("Orders");
        customerPage = (CustomerPageObject) orderPage.openDynamicSideBarPage("Customer info");
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
