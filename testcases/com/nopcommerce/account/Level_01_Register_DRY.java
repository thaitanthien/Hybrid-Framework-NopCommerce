package com.nopcommerce.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_01_Register_DRY {
    WebDriver driver;
    String emailAddress = "johnKennedy" + new Random().nextInt(9999)+ "@gmail.com";
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void Register_01_Empty() {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
    }

    @Test
    public void Register_02_InvalidEmail() {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("john@ennedy@us");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Wrong email");
    }

    @Test
    public void Register_03_InvalidPassword() {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("johnennedy@us");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), "Password must meet the following rules: \nmust have at least 6 characters");
    }

    @Test
    public void Register_04_IncorrectConfirmPassword() {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("johnennedy@us");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
    }

    @Test
    public void Register_05_Success() {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
    }

    @AfterClass
    public void afterClass() {

    }

}
