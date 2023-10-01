//package reportConfig;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//import commons.BaseTest;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//
//import javax.annotation.Nullable;
//
//public class ReportNGListener implements ITestListener {
//
//    @Override
//    public void onStart(ITestContext context) {
//        System.out.println("---------- " + context.getName() + " STARTED test ----------");
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("---------- " + context.getName() + " FINISHED test ----------");
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        System.out.println("---------- " + result.getName() + " STARTED test ----------");
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        System.out.println("---------- " + result.getName() + " SUCCESS test ----------");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        System.out.println("---------- " + result.getName() + " FAILED test ----------");
//        System.setProperty("org.uncommons.reportng.escape-output", "false");
//
//        Object testClass = result.getInstance();
//        WebDriver webDriver = ((BaseTest) testClass).getDriver();
//
//        Reporter.getCurrentTestResult();
//
//        File screenshotFile = captureScreenshot(webDriver, result.getName());
//        if (screenshotFile != null) {
//            Reporter.log("<a href='"+  screenshotFile.getName() + "'> <img src='"+  screenshotFile.getName() + "' height='100' width='150'/> </a>");
//           // Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        System.out.println("---------- " + result.getName() + " SKIPPED test ----------");
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        System.out.println("---------- " + result.getName() + " FAILED WITH SUCCESS PERCENTAGE test ----------");
//    }
//
//    @Nullable
//    public File captureScreenshot(WebDriver driver, String screenshotName) {
//        try {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String fileName = screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
//            File resourceFolder = new File("test-output/html");
//            if (resourceFolder.exists()) {
//                resourceFolder.mkdirs();
//            }
//            File destination = new File(resourceFolder, fileName);
//            FileUtils.copyFile(source, destination);
//            return destination;
//        } catch (IOException e) {
//            System.out.println("Exception while taking screenshot: " + e.getMessage());
//            return null;
//        }
//    }
//}
