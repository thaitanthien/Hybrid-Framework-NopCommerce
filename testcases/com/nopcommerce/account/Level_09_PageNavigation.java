package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;

public class Level_09_PageNavigation extends BaseTest {
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
        orderPage = customerPage.openOrdersPage();
        addressPage = orderPage.openAddressPage();
        rewardPointPage = addressPage.openRewardPointPage();
        orderPage = rewardPointPage.openOrdersPage();
        customerPage = orderPage.openCustomerPage();
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
