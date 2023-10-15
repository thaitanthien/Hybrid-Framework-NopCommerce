package reportConfig;
// ver2
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.relevantcodes.extentreports.LogStatus;
//
//import commons.BaseTest;
//
//public class ExtentTestListener implements ITestListener {
//
//    @Override
//    public void onStart(ITestContext context) {
//
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        ExtentManager.endTest();
//        ExtentManager.getReporter().flush();
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        ExtentManager.getTest().log(LogStatus.PASS, "Test Passed");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        Object testClass = result.getInstance();
//        WebDriver webDriver = ((BaseTest) testClass).getDriver();
//
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
//        ExtentManager.getTest().log(LogStatus.FAIL, "Test Failed", ExtentManager.getTest().addBase64ScreenShot(base64Screenshot));
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        ExtentManager.getTest().log(LogStatus.SKIP, "Test Skipped");
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//    }
//
//}
// Ver3
//import java.io.IOException;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//
//import commons.BaseTest;
//
//public class ExtentTestListener extends BaseTest implements ITestListener {
//    private static ExtentReports extent = ExtentManager.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
//
//    @Override
//    public synchronized void onStart(ITestContext context) {
//    }
//
//    @Override
//    public synchronized void onFinish(ITestContext context) {
//        extent.flush();
//    }
//
//    @Override
//    public synchronized void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
//        test.set(extentTest);
//    }
//
//    @Override
//    public synchronized void onTestSuccess(ITestResult result) {
//
//        test.get().pass("Test passed");
//    }
//
//    @Override
//    public synchronized void onTestFailure(ITestResult result) {
//        Object testClass = result.getInstance();
//        WebDriver driver = ((BaseTest) testClass).getDriver();
//        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//
//        try {
//            test.get().fail(result.getThrowable());
//            test.get().log(Status.FAIL, "Test failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public synchronized void onTestSkipped(ITestResult result) {
//        test.get().skip(result.getThrowable());
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//
//    }
//}
//ver4
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.testng.IReporter;
//import org.testng.IResultMap;
//import org.testng.ISuite;
//import org.testng.ISuiteResult;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.xml.XmlSuite;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentTestListener implements IReporter {
//    private ExtentReports extent;
//    private ExtentHtmlReporter htmlReport;
//
//    @Override
//    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//        htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReport/ExtentReportV4.html");
//        htmlReport.config().setTheme(Theme.DARK);
//        htmlReport.config().setDocumentTitle("NopCommerce Testing");
//        htmlReport.config().setReportName("Functional UI");
//        htmlReport.config().setEncoding("utf-8");
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReport);
//
//        for (ISuite suite : suites) {
//            Map<String, ISuiteResult> result = suite.getResults();
//
//            for (ISuiteResult r : result.values()) {
//                ITestContext context = r.getTestContext();
//
//                buildTestNodes(context.getFailedTests(), Status.FAIL);
//                buildTestNodes(context.getSkippedTests(), Status.SKIP);
//                buildTestNodes(context.getPassedTests(), Status.PASS);
//            }
//        }
//
//        for (String s : Reporter.getOutput()) {
//            s = s + "<br>";
//            extent.setTestRunnerOutput(s);
//        }
//
//        extent.flush();
//    }
//
//    private void buildTestNodes(IResultMap tests, Status status) {
//        ExtentTest test;
//
//        if (tests.size() > 0) {
//            for (ITestResult result : tests.getAllResults()) {
//                test = extent.createTest(result.getMethod().getMethodName());
//
//                for (String group : result.getMethod().getGroups())
//                    test.assignCategory(group);
//
//                if (result.getThrowable() != null) {
//                    test.log(status, result.getThrowable());
//                } else {
//                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
//                }
//
//                test.getModel().setStartTime(getTime(result.getStartMillis()));
//                test.getModel().setEndTime(getTime(result.getEndMillis()));
//            }
//        }
//    }
//
//    private Date getTime(long millis) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(millis);
//        return calendar.getTime();
//    }
//}
//ver5


//import static reportConfig.ExtentTestManager.getTest;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//
//import commons.BaseTest;
//
//public class ExtentTestListener extends BaseTest implements ITestListener {
//
//    @Override
//    public void onStart(ITestContext iTestContext) {
//        iTestContext.setAttribute("WebDriver", this.getDriver());
//    }
//
//    @Override
//    public void onFinish(ITestContext iTestContext) {
//        ExtentManager.extentReports.flush();
//    }
//
//    @Override
//    public void onTestStart(ITestResult iTestResult) {
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult iTestResult) {
//        getTest().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName() + " - Passed", ExtentColor.GREEN));
//    }
//
//    @Override
//    public void onTestFailure(ITestResult iTestResult) {
//        Object testClass = iTestResult.getInstance();
//        WebDriver driver = ((BaseTest) testClass).getDriver();
//
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//        getTest().log(Status.FAIL, "Screenshot and Exception", iTestResult.getThrowable(), getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
//        getTest().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " - Failed", ExtentColor.RED));
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult iTestResult) {
//        getTest().log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName() + " - Skipped", ExtentColor.ORANGE));
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//        getTest().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " - Failed with Percentage", ExtentColor.RED));
//    }
//}