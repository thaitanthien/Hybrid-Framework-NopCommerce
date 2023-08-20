package commons;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;
//    private final String projectPath = System.getProperty("user.dir");

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

}
