package reportConfig;
//ver2
//import java.util.HashMap;
//import java.util.Map;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//
//public class ExtentManager {
//    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
//    private static ExtentReports extent = ExtentManager.getReporter();
//
//    public synchronized static ExtentReports getReporter() {
//        if (extent == null) {
//            extent = new ExtentReports(System.getProperty("user.dir") + "/extentReport/ExtentReportV2.html", true);
//        }
//        return extent;
//    }
//
//    public static synchronized ExtentTest getTest() {
//        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
//    }
//
//    public static synchronized void endTest() {
//        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
//    }
//
//    public static synchronized ExtentTest startTest(String testName, String desc) {
//        ExtentTest test = extent.startTest(testName, desc);
//        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
//        return test;
//    }
//}
// ver3
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentManager {
//    private static ExtentReports extent;
//    private static String extentReportPath = System.getProperty("user.dir") + "/extentReport/ExtentReportV3.html";
//
//    public static ExtentReports getInstance() {
//        if (extent == null)
//            createInstance();
//        return extent;
//    }
//
//    public static ExtentReports createInstance() {
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportPath);
//        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//        htmlReporter.config().setChartVisibilityOnOpen(true);
//        htmlReporter.config().setTheme(Theme.DARK);
//        htmlReporter.config().setDocumentTitle("NopCommerce HTML Report");
//        htmlReporter.config().setEncoding("utf-8"); // encode when test has Japannes/Vietnames
//        htmlReporter.config().setReportName("NopCommerce HTML Report");
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
//        return extent;
//    }
//}
//ver5
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import commons.GlobalConstants;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.RELATIVE_PROJECT_PATH + "/extentReport/ExtentReportV5.html");
        reporter.config().setReportName("NopCommerce HTML Report");
        reporter.config().setDocumentTitle("NopCommerce HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Automation FC");
        extentReports.setSystemInfo("Project", "NopCommerce");
        extentReports.setSystemInfo("Team", "Team Name");
        extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);
        return extentReports;
    }
}