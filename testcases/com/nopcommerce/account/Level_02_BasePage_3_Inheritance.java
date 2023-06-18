package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_02_BasePage_3_Inheritance extends BasePage {
    private WebDriver driver;

    private final String emailAddress = "johnKennedy" + new Random().nextInt(9999)+ "@gmail.com";
    private final String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void Register_01_Empty() {
        navigateToURL(driver, "https://demo.nopcommerce.com");
        sleepInSecond(2);
        clickToElement(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//button[@id='register-button']");
        sleepInSecond(1);

        Assert.assertEquals(getElementText(driver,"//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void Register_02_InvalidEmail() {
        navigateToURL(driver, "https://demo.nopcommerce.com");
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "John");
        sendKeyToElement(driver, "//input[@id='LastName']", "Kennedy");
        sendKeyToElement(driver, "//input[@id='Email']", "john@kennedy@us");
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void Register_03_InvalidPassword() {
        navigateToURL(driver, "https://demo.nopcommerce.com");
        sleepInSecond(2);
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "John");
        sendKeyToElement(driver, "//input[@id='LastName']", "Kennedy");
        sendKeyToElement(driver, "//input[@id='Email']", "johnkennedy@us");
        sendKeyToElement(driver, "//input[@id='Password']", "1234");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");
        clickToElement(driver, "//button[@id='register-button']");
        sleepInSecond(2);
        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"),
                "Password must meet the following rules:\n" + "must have at least 6 characters");
    }

    @Test
    public void Register_04_IncorrectConfirmPassword() {
        navigateToURL(driver, "https://demo.nopcommerce.com");
        sleepInSecond(2);
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "John");
        sendKeyToElement(driver, "//input[@id='LastName']", "Kennedy");
        sendKeyToElement(driver, "//input[@id='Email']", "johnkennedy@us");
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
                "The password and confirmation password do not match.");
    }

    @Test
    public void Register_05_Success() {
        navigateToURL(driver, "https://demo.nopcommerce.com");
        sleepInSecond(2);
        clickToElement(driver, "//a[@class='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "John");
        sendKeyToElement(driver, "//input[@id='LastName']", "Kennedy");
        sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
