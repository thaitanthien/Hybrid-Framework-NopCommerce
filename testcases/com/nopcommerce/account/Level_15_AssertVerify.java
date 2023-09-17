package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;

public class Level_15_AssertVerify extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private final String emailAddress = getEmailRandom();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Register_01_Register_Success() {
        verifyFalse(homePage.isRegisterLinkDisplayed()); // Failed

        registerPage = homePage.clickRegisterLink();

        registerPage.clickRegisterBtn();
        verifyEquals(registerPage.getFirstNameErrorMsgText(), "First name is required."); // Passed
        verifyEquals(registerPage.getLastNameErrorMsgText(), "Last name is required"); // Failed

        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickRegisterBtn();
        verifyEquals(registerPage.getRegisterSuccessMsgText(), "Your registration completed."); // Failed
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
