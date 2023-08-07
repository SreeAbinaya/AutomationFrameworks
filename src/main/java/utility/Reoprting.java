package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reoprting {
	
	
	public static ExtentReports prepareReportInitilaization() {
		String path = System.getProperty("user.dir")+"//extentReport//ExtentReport.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Kyro's Automation test Report");
		report.config().setDocumentTitle("Test Reults");
		
		ExtentReports data = new ExtentReports();
		data.attachReporter(report);
		data.setSystemInfo("Tester", "Abinaya");
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver, String testcaseName) throws IOException {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File screenshot =  capture.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"extentReport//"+testcaseName+".png");
		FileUtils.copyFile(screenshot, destination);
		return System.getProperty("user.dir")+"extentReport//"+testcaseName+".png";
	}

}
