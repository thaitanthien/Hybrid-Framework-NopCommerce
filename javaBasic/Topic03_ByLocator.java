import org.openqa.selenium.By;

public class Topic03_ByLocator {

    public static void main(String[] arg) {
        String locator1 = "css=[class='ico-register']";
        String locator2 = "xpath=//div[@class='ico-register']";
        String locator3 = "Css=button[class='ico-register']";
        String locator4 = "class='ico-register'";
        String locator5 = "CLASS='ico-register'";
        System.out.println(getByLocator(locator1));
        System.out.println(getByLocator(locator2));
        System.out.println(getByLocator(locator3));
        System.out.println(getByLocator(locator4));
        System.out.println(getByLocator(locator5));
    }

    public static By getByLocator(String locatorType) {
        By by = null;
        String locator = locatorType.substring(locatorType.indexOf("=") + 1).trim();
        if (locatorType.startsWith("xpath") || locatorType.startsWith("Xpath") || locatorType.startsWith("XPath") || locatorType.startsWith("XPATH")) {
            by = By.xpath(locator);
        } else if (locatorType.startsWith("id") || locatorType.startsWith("Id") || locatorType.startsWith("ID")) {
            by = By.id(locator);
        } else if (locatorType.startsWith("name") || locatorType.startsWith("Name") || locatorType.startsWith("NAME")) {
            by = By.name(locator);
        } else if (locatorType.startsWith("class") || locatorType.startsWith("Class") || locatorType.startsWith("CLASS")) {
            by = By.className(locator);
        } else if (locatorType.startsWith("css") || locatorType.startsWith("Css") || locatorType.startsWith("CSS")) {
            by = By.cssSelector(locator);
        }  else if (locatorType.startsWith("tagname") || locatorType.startsWith("TagName") || locatorType.startsWith("TAGNAME")) {
            by = By.tagName(locator);
        } else {
            throw new RuntimeException("Locator type is invalid.");
        }

        return by;
    }
}
