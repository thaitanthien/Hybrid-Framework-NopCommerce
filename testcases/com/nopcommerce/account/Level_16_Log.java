package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;

public class Level_16_Log extends BaseTest {
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

    @Test
    public void Register_01_Register_Success() {
        log.info("Register - Step 1: Verify Register link is displayed");
        verifyFalse(homePage.isRegisterLinkDisplayed()); // Failed

        log.info("Register - Step 2: Click to Register link");
        registerPage = homePage.clickRegisterLink();

        log.info("Register - Step 3: Click to Register button");
        registerPage.clickRegisterBtn();

        log.info("Register - Step 4: Verify error msg at Firstname textbox");
        verifyEquals(registerPage.getFirstNameErrorMsgText(), "First name is required."); // Passed

        log.info("Register - Step 5: Verify error msg at Lastname textbox");
        verifyEquals(registerPage.getLastNameErrorMsgText(), "Last name is required"); // Failed

        log.info("Register - Step 6: Enter to First Name textbox with value: " + firstName);
        registerPage.enterToFirstNameTextbox(firstName);

        log.info("Register - Step 7: Enter to Last Name textbox with value: " + lastName);
        registerPage.enterToLastNameTextbox(lastName);

        log.info("Register - Step 8: Enter to Email Address textbox with value: " + emailAddress);
        registerPage.enterToEmailTextbox(emailAddress);

        log.info("Register - Step 9: Enter to Password textbox with value: " + password);
        registerPage.enterToPasswordTextbox(password);

        log.info("Register - Step 10: Enter to Confirm Password textbox with value: " + password);
        registerPage.enterToConfirmPasswordTextbox(password);

        log.info("Register - Step 11: Click to Register button");
        registerPage.clickRegisterBtn();

        log.info("Register - Step 12: Verify register complete");
        verifyEquals(registerPage.getRegisterSuccessMsgText(), "Your registration completed."); // Failed
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
