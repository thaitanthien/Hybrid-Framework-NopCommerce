package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;

import java.lang.reflect.Method;

public class Level_18_ExtentV3 extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private final String emailAddress = getEmailRandom();
    private String firstName, lastName, password;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
        registerPage = PageGeneratorManager.getRegisterPage(driver);
        firstName = "John";
        lastName = "Kennedy";
        password = "123456";
    }

    @Test
    public void User_01_Register_Validate(Method method) {
        Assert.assertFalse(homePage.isRegisterLinkDisplayed()); // Failed

        registerPage = homePage.clickRegisterLink();

        registerPage.clickRegisterBtn();

        Assert.assertEquals(registerPage.getFirstNameErrorMsgText(), "First name is required."); // Passed

        Assert.assertEquals(registerPage.getLastNameErrorMsgText(), "Last name is required"); // Failed
    }

    @Test
    public void User_02_Register_Success(Method method) {
        registerPage = homePage.clickRegisterLink();
        registerPage.enterToFirstNameTextbox(firstName);

        registerPage.enterToLastNameTextbox(lastName);

        registerPage.enterToEmailTextbox(emailAddress);

        registerPage.enterToPasswordTextbox(password);

        registerPage.enterToConfirmPasswordTextbox(password);

        registerPage.clickRegisterBtn();

        Assert.assertEquals(registerPage.getRegisterSuccessMsgText(), "Your registration completed"); // Passed
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
