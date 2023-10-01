package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    protected final Logger log;
    private WebDriver driver;

    public BaseTest() {
        // ver1
//        log = LogFactory.getLog(getClass());
// same with:
//      log = LogFactory.getLog(BaseTest.class);
        // ver2
        log = LogManager.getLogger(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case FIREFOX:
//                  manual download and setting:
//                System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//                driver = new FirefoxDriver();

//                  WebDriverManager ver4:
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                  WebDriverManger ver5: download driver, setting biến môi trường và khởi tạo browser lên
//                WebDriverManager.firefoxdriver().create();
//                  Selenium Manger:
                driver = new FirefoxDriver();
                break;
            case CHROME:
//                WebDriverManager.chromedriver().create();
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
//                WebDriverManager.edgedriver().create();
                break;
            case SAFARI:
//                WebDriverManager.safaridriver().create();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }

        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com");
        return driver;
    }
    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case SAFARI:
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }

        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(url);
        return driver;
    }

    protected String getEmailRandom() {
        return "john" + new Random().nextInt(99999)+ "@kennedy.us";
    }

    protected void closeBrowser() {
        if (!(driver == null)) {
            driver.quit();
        }
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("---------------PASSED---------------");
        } catch (Throwable e) {
            log.info("---------------FAILED---------------");
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info("---------------PASSED---------------");
        } catch (Throwable e) {
            log.info("---------------FAILED---------------");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("---------------PASSED---------------");
        } catch (Throwable e) {
            log.info("---------------FAILED---------------");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

//    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        log.info("Starting delete all files in ReportNG screenshot");
        deleteAllFileInFolder("ReportNGScreenShots");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.RELATIVE_PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
