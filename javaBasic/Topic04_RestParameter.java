import org.testng.annotations.Test;

public class Topic04_RestParameter {

    String dynamicSideBarLink = "//div[@class='side-2']//a[text()='%s']";
    String dynamicSideBarLink2 = "//div[@class='%s']//a[text()='%s']//a[id='%s']";

    @Test
    public void TC01_RestParam() {
        clickElement(dynamicSideBarLink, "Addresses");
        clickElement(dynamicSideBarLink2, "Header", "Addresses", "Row");
    }

    // Dynamic with 1 param
    public void clickElement(String locator, String pageName) {
        locator = String.format(locator, pageName);
        System.out.println("Click to: " + locator);
    }

    //Dynamic with n params
    public void clickElement(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        System.out.println("Click to: " + locator);
    }
}
