package utilityTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utility.Reoprting;

public class Listeners extends BaseTests implements ITestListener{

	ExtentReports extent = Reoprting.prepareReportInitilaization();
	ExtentTest test;
	WebDriver driver;
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("starting");
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Passing");
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("failing");
		test.fail(result.getThrowable());
		String tcName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = Reoprting.captureScreenshot(driver, tcName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, tcName);
	}
	

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
