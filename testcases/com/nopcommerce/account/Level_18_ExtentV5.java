//package com.nopcommerce.account;
//
//import com.aventstack.extentreports.Status;
//import commons.BaseTest;
//import commons.PageGeneratorManager;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import pageObjects.user.HomePageObject;
//import pageObjects.user.RegisterPageObject;
//
//import reportConfig.ExtentTestManager;
//
//import java.lang.reflect.Method;
//
//public class Level_18_ExtentV5 extends BaseTest {
//    private WebDriver driver;
//    private HomePageObject homePage;
//    private RegisterPageObject registerPage;
//    private final String emailAddress = getEmailRandom();
//    private String firstName, lastName, password;
//
//    @Parameters("browser")
//    @BeforeClass
//    public void beforeClass(String browserName) {
//        driver = getBrowserDriver(browserName);
//        homePage = PageGeneratorManager.getHomePage(driver);
//        firstName = "John";
//        lastName = "Kennedy";
//        password = "123456";
//    }
//
//    @Test
//    public void User_01_Register_Validate(Method method) {
//        ExtentTestManager.startTest(method.getName(), "User_01_Register_Validate");
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 1: Verify Register link is displayed");
//        Assert.assertFalse(homePage.isRegisterLinkDisplayed()); // Failed
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 2: Click to Register link");
//        registerPage = homePage.clickRegisterLink();
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 3: Click to Register button");
//        registerPage.clickRegisterBtn();
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 4: Verify error msg at Firstname textbox");
//        Assert.assertEquals(registerPage.getFirstNameErrorMsgText(), "First name is required."); // Passed
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 5: Verify error msg at Lastname textbox");
//        Assert.assertEquals(registerPage.getLastNameErrorMsgText(), "Last name is required"); // Failed
//    }
//
//    @Test
//    public void User_02_Register_Success(Method method) {
//        ExtentTestManager.startTest(method.getName(), "User_02_Register_Success");
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 6: Enter to First Name textbox with value: " + firstName);
//        registerPage = homePage.clickRegisterLink();
//        registerPage.enterToFirstNameTextbox(firstName);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 7: Enter to Last Name textbox with value: " + lastName);
//        registerPage.enterToLastNameTextbox(lastName);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 8: Enter to Email Address textbox with value: " + emailAddress);
//        registerPage.enterToEmailTextbox(emailAddress);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 9: Enter to Password textbox with value: " + password);
//        registerPage.enterToPasswordTextbox(password);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10: Enter to Confirm Password textbox with value: " + password);
//        registerPage.enterToConfirmPasswordTextbox(password);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11: Click to Register button");
//        registerPage.clickRegisterBtn();
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12: Verify register complete");
//        Assert.assertEquals(registerPage.getRegisterSuccessMsgText(), "Your registration completed"); // Passed
//    }
//
//    @AfterClass
//    public void afterClass() {
//        closeBrowser();
//    }
//
//}
