package com.nopcommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_07_PageManager_1 extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private final String emailAddress = getEmailRandom();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = new HomePageObject(driver);
    }

    @Test
    public void Register_01_Empty() {
        registerPage = homePage.clickRegisterLink();
        registerPage.clickRegisterBtn();
        Assert.assertEquals(registerPage.getFirstNameErrorMsgText(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMsgText(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMsgText(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMsgText(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsgText(), "Password is required.");
    }

    @Test
    public void Register_02_InvalidEmail() {
        homePage = registerPage.clickTopNopCommerceLogo();
        registerPage = homePage.clickRegisterLink();
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox("john@kennedy@us");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickRegisterBtn();
        Assert.assertEquals(registerPage.getEmailErrorMsgText(), "Wrong email");
    }

    @Test
    public void Register_03_InvalidPassword() {
        homePage = registerPage.clickTopNopCommerceLogo();
        registerPage = homePage.clickRegisterLink();
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox("johnkennedy@us");
        registerPage.enterToPasswordTextbox("123");
        registerPage.enterToConfirmPasswordTextbox("123");
        registerPage.clickRegisterBtn();
        Assert.assertEquals(registerPage.getPasswordErrorMsgText(),
                "Password must meet the following rules:\n" + "must have at least 6 characters");
    }

    @Test
    public void Register_04_IncorrectConfirmPassword() {
        homePage = registerPage.clickTopNopCommerceLogo();
        registerPage = homePage.clickRegisterLink();
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox("johnkennedy@us");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("1234");
        registerPage.clickRegisterBtn();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsgText(), "The password and confirmation password do not match.");
    }

    @Test
    public void Register_05_Success() {
        homePage = registerPage.clickTopNopCommerceLogo();
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
    public void Register_06_Login_Success() {
        homePage = registerPage.clickTopNopCommerceLogo();
        loginPage = homePage.clickLoginLink();
        loginPage.enterEmailTextbox(emailAddress);
        loginPage.enterPasswordTextbox("123456");
        homePage = loginPage.clickLoginBtn();
        customerPage = homePage.clickAccountLink();
        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(), "John");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(), "Kennedy");
        Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(), "emailAddress");
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
