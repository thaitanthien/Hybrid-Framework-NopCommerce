package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    WebDriverWait explicitWait;

    public void get(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }

    public void navigateForward(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void sendKeyToAlert(WebDriver driver, String key) {
        driver.switchTo().alert().sendKeys(key);
    }

    public void getWindowHandle(WebDriver driver) {
        driver.getWindowHandle();
    }

    public void switchWindowByID(WebDriver driver, String pageID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if(!id.equalsIgnoreCase(pageID)) {
                driver.switchTo().window(id);
                sleepInSecond(1);
            }
        }
    }

    public void switchWindowByTitle(WebDriver driver, String pageTitle) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            driver.switchTo().window(id);
            sleepInSecond(1);
            if (driver.getTitle().equalsIgnoreCase(pageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            driver.switchTo().window(id);
            driver.close();
        }
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator));
    }
    public By getElementByXpath(WebDriver driver, String locator) {
        return By.xpath(locator);
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(By.xpath(locator));
    }


    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String key) {
        getWebElement(driver, locator).sendKeys(key);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String option) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(option);
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String listItemsLocator, String option) {
        getWebElement(driver, parentLocator).click();
        List<WebElement> dropdownItems = explicitWait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getElementByXpath(driver, listItemsLocator)));
        for (WebElement item : dropdownItems) {
            if (item.getText().equalsIgnoreCase(option)) {
                item.click();
                break;
            }
        }
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getCssValue(WebDriver driver, String locator, String cssPropertyName) {
        return getWebElement(driver, locator).getCssValue(cssPropertyName);
    }

    public String getHexaColorFromRGBA(WebDriver driver, String locator) {
        String backgroundColorInRGBA = getCssValue(driver, locator, "background-color");
        return Color.fromString(backgroundColorInRGBA).asHex();
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListWebElement(driver, locator).size();
    }

    /**
     * Apply for checkbox and radio button
     * @param driver
     * @param locator
     */
    public void checkToElement(WebDriver driver, String locator) {
        if (!getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    /**
     * only apply for checkbox
     * @param driver
     * @param locator
     */
    public void uncheckToElement(WebDriver driver, String locator) {
        if (getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public void doubleClickElement(WebDriver driver) {
        new Actions(driver).doubleClick().perform();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void rightClickElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void sendKeyToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getWebElement(driver, locator)));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

//  JS
    public Object executeBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public void navigateToURL(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
    }

    public String getInnerTextOfPage(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return document.documentElement.InnerText;").toString();
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, document.body.scrollHeight);");
    }

    public void clickElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String key) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + key + "')", getWebElement(driver, locator));
    }

    public void highlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[1].setAttribute('style', arguments[0])", "border:4px solid red; border-style: dashed;", element);
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[1].setAttribute('style', arguments[0])", originalStyle, element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attribute) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attribute + "');", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop (WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", getWebElement(driver, locator));
    }

    public void scrollToElementOnBottom (WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", getWebElement(driver, locator));
    }

    public boolean isExpectedTextInInnerText(WebDriver driver, String text) {
        String actualText = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + text + "')[0];");
        return actualText.equals(text);
    }

    public boolean isImgLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefine' && arguments[0].naturalWidth > 0;", getWebElement(driver, locator));
    }

    public String getValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public WebElement waitForElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getElementByXpath(driver, locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getElementByXpath(driver, locator)));
    }

    public List<WebElement> waitForElementsVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locator)));
    }

    public void waitForElementsInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getElementByXpath(driver, locator)));
    }

    public void waitForAlertPresence(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }

}
