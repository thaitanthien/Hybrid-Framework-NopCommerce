package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;

import java.lang.reflect.Method;

@Epic("Account")
@Feature("Create Account")
public class Level_19_Allure extends BaseTest {
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
        firstName = "John";
        lastName = "Kennedy";
        password = "123456";
    }

    @Description("User 01 - Validate register form")
    @Story("register")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void User_01_Register_Validate() {
        Assert.assertFalse(homePage.isRegisterLinkDisplayed()); // Failed

        registerPage = homePage.clickRegisterLink();

        registerPage.clickRegisterBtn();

        Assert.assertEquals(registerPage.getFirstNameErrorMsgText(), "First name is required."); // Passed

        Assert.assertEquals(registerPage.getLastNameErrorMsgText(), "Last name is required"); // Failed
    }

    @Description("User 02 - Validate register success")
    @Story("register")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void User_02_Register_Success() {
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
