package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;

public class Level_03_PageObject extends BasePage {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private final String emailAddress = "johnKennedy" + new Random().nextInt(9999)+ "@gmail.com";
    private final String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        navigateToURL(driver, "https://demo.nopcommerce.com");
        homePage = new HomePageObject(driver);
    }

    @Test
    public void Register_01_Empty() {
        homePage.clickRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.clickRegisterBtn();
        Assert.assertEquals(registerPage.getFirstNameErrorMsgText(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMsgText(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMsgText(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMsgText(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsgText(), "Password is required.");
    }

    @Test
    public void Register_02_InvalidEmail() {
        registerPage.clickTopNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickRegisterLink();
        registerPage = new RegisterPageObject(driver);
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
        registerPage.clickTopNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickRegisterLink();
        registerPage = new RegisterPageObject(driver);
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
        registerPage.clickTopNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickRegisterLink();
        registerPage = new RegisterPageObject(driver);
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
        registerPage.clickTopNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickRegisterLink();
        registerPage = new RegisterPageObject(driver);
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
        registerPage.clickTopNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickLoginLink();
        loginPage = new LoginPageObject();
        loginPage.enterEmailTextbox(emailAddress);
        loginPage.enterPasswordTextbox("123456");
        loginPage.clickLoginBtn();
        homePage = new HomePageObject(driver);
        homePage.clickAccountLink();
        customerPage = new CustomerPageObject(driver);
        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(), "John");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(), "Kennedy");
        Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(), "emailAddress");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
