//package com.nopcommerce.account;
//
//import com.relevantcodes.extentreports.LogStatus;
//import commons.BaseTest;
//import commons.PageGeneratorManager;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//import pageObjects.user.HomePageObject;
//import pageObjects.user.RegisterPageObject;
//import reportConfig.ExtentManager;
//
//import java.lang.reflect.Method;
//
//public class Level_18_ExtentV2 extends BaseTest {
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
//        registerPage = PageGeneratorManager.getRegisterPage(driver);
//        firstName = "John";
//        lastName = "Kennedy";
//        password = "123456";
//    }
//
//    @Test
//    public void User_01_Register_Validate(Method method) {
//        ExtentManager.startTest(method.getName(), "User_01_Register_Validate");
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 1: Verify Register link is displayed");
//        Assert.assertFalse(homePage.isRegisterLinkDisplayed()); // Failed
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 2: Click to Register link");
//        registerPage = homePage.clickRegisterLink();
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 3: Click to Register button");
//        registerPage.clickRegisterBtn();
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 4: Verify error msg at Firstname textbox");
//        Assert.assertEquals(registerPage.getFirstNameErrorMsgText(), "First name is required."); // Passed
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 5: Verify error msg at Lastname textbox");
//        Assert.assertEquals(registerPage.getLastNameErrorMsgText(), "Last name is required"); // Failed
//    }
//
//    @Test
//    public void User_02_Register_Success(Method method) {
//        ExtentManager.startTest(method.getName(), "User_02_Register_Success");
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 6: Enter to First Name textbox with value: " + firstName);
//        registerPage.enterToFirstNameTextbox(firstName);
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 7: Enter to Last Name textbox with value: " + lastName);
//        registerPage.enterToLastNameTextbox(lastName);
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 8: Enter to Email Address textbox with value: " + emailAddress);
//        registerPage.enterToEmailTextbox(emailAddress);
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 9: Enter to Password textbox with value: " + password);
//        registerPage.enterToPasswordTextbox(password);
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 10: Enter to Confirm Password textbox with value: " + password);
//        registerPage.enterToConfirmPasswordTextbox(password);
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 11: Click to Register button");
//        registerPage.clickRegisterBtn();
//
//        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 12: Verify register complete");
//        Assert.assertEquals(registerPage.getRegisterSuccessMsgText(), "Your registration completed"); // Passed
//    }
//
//    @AfterClass
//    public void afterClass() {
//        closeBrowser();
//    }
//
//}
