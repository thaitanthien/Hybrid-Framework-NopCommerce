package com.facebook.home;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.HomePageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_20_ElementUndisplayed extends BaseTest {
    private WebDriver driver;
    HomePageObject homePageObject;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePageObject = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Home_1_ElementDisPlayed() {
        homePageObject.clickToCreateNewAccountBtn();

        verifyTrue(homePageObject.isFirstNameTextboxDisplayed());
        verifyTrue(homePageObject.isSurNameTextboxDisplayed());
        verifyTrue(homePageObject.isEmailTextboxDisplayed());

        homePageObject.enterToEmailTextbox("thienthai@gmail.com");

        log.info("Verify confirm email textbox is displayed");
        verifyTrue(homePageObject.isConfirmEmailTextboxDisplayed());
    }

    @Test
    public void Home_2_ElementUnDisPlayedInHTML() {
        homePageObject.enterToEmailTextbox("");

        log.info("Verify confirm email textbox is not displayed");
        verifyFalse(homePageObject.isConfirmEmailTextboxDisplayed());
    }

//    @Test
    public void Home_3_ElementUnDisPlayedNotInHTML_01() {
        homePageObject.clickCloseSignupPopup();

        log.info("Verify Firstname textbox is not displayed");
        verifyFalse(homePageObject.isFirstNameTextboxDisplayed());

        log.info("Verify Surname textbox is not displayed");
        verifyFalse(homePageObject.isSurNameTextboxDisplayed());

        log.info("Verify Email textbox is not displayed");
        verifyFalse(homePageObject.isEmailTextboxDisplayed());
    }

    @Test
    public void Home_3_ElementUnDisPlayedNotInHTML_02() {
        homePageObject.clickCloseSignupPopup();

        log.info("Verify Firstname textbox is not displayed");
        verifyTrue(homePageObject.isFirstNameTextboxUnDisplayed());

        log.info("Verify Surname textbox is not displayed");
        verifyTrue(homePageObject.isSurNameTextboxUnDisplayed());

        log.info("Verify Email textbox is not displayed");
        verifyTrue(homePageObject.isEmailTextboxUnDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

}
