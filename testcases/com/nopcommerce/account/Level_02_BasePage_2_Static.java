package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_02_BasePage_2_Static {
    private WebDriver driver;
    private final BasePage basePage = BasePage.getBasePage();
    private final String emailAddress = "johnKennedy" + new Random().nextInt(9999)+ "@gmail.com";
    private final String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void Register_01_Empty() {
        basePage.navigateToURL(driver, "https://demo.nopcommerce.com");
        basePage.clickToElement(driver, ".ico-register");
        basePage.clickToElement(driver, ".register-button");

        Assert.assertEquals(basePage.getWebElement(driver, "span#FirstName-error"), "First name is required.");
        Assert.assertEquals(basePage.getWebElement(driver,"span#LastName-error"), "Last name is required.");
        Assert.assertEquals(basePage.getWebElement(driver,"span#Email-error"), "Email is required.");
        Assert.assertEquals(basePage.getWebElement(driver,"span#Password-error"), "Password is required.");
        Assert.assertEquals(basePage.getWebElement(driver,"span#ConfirmPassword-error"), "Password is required.");
    }

    @Test
    public void Register_02_InvalidEmail() {
        basePage.navigateToURL(driver, "https://demo.nopcommerce.com");
        basePage.clickToElement(driver, ".ico-register");
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("john@ennedy@us");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        basePage.clickToElement(driver, "#register-button");
        Assert.assertEquals(basePage.getElementText(driver, "Email-error"), "Wrong email");
    }

    @Test
    public void Register_03_InvalidPassword() {
        basePage.navigateToURL(driver, "https://demo.nopcommerce.com");
        basePage.clickToElement(driver, ".ico-register");
        basePage.sendKeyToElement(driver, "input#FirstName", "John");
        basePage.sendKeyToElement(driver, "input#LastName", "Kennedy");
        basePage.sendKeyToElement(driver, "input#Email", "johnennedy@us");
        basePage.sendKeyToElement(driver, "input#Password", "123");
        basePage.sendKeyToElement(driver, "input#ConfirmPassword", "123");
        basePage.clickToElement(driver, "#register-button");
        Assert.assertEquals(basePage.getElementText(driver, "#Password-error"),
                "Password must meet the following rules: \nmust have at least 6 characters");
    }

    @Test
    public void Register_04_IncorrectConfirmPassword() {
        basePage.navigateToURL(driver, "https://demo.nopcommerce.com");
        basePage.clickToElement(driver, ".ico-register");
        basePage.sendKeyToElement(driver, "input#FirstName", "John");
        basePage.sendKeyToElement(driver, "input#LastName", "Kennedy");
        basePage.sendKeyToElement(driver, "input#Email", "johnennedy@us");
        basePage.sendKeyToElement(driver, "input#Password", "123456");
        basePage.sendKeyToElement(driver, "input#ConfirmPassword", "123");
        basePage.clickToElement(driver, "#register-button");
        Assert.assertEquals(basePage.getElementText(driver, "#ConfirmPassword-error"), "The password and confirmation password do not match.");
    }

    @Test
    public void Register_05_Success() {
        basePage.navigateToURL(driver, "https://demo.nopcommerce.com");
        basePage.clickToElement(driver, ".ico-register");
        basePage.sendKeyToElement(driver, "input#FirstName", "John");
        basePage.sendKeyToElement(driver, "input#LastName", "Kennedy");
        basePage.sendKeyToElement(driver, "input#Email", emailAddress);
        basePage.sendKeyToElement(driver, "input#Password", "123456");
        basePage.sendKeyToElement(driver, "input#ConfirmPassword", "123");
        basePage.clickToElement(driver, "#register-button");
        Assert.assertEquals(basePage.getElementText(driver, "div.result"), "Your registration completed");
    }

    @AfterClass
    public void afterClass() {

    }

}
