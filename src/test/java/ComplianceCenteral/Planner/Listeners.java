package ComplianceCenteral.Planner;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReporterNG;

public class Listeners extends BaseClass implements ITestListener{

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test ;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	test=extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test PAssed");
	}

	@Override
	
	public void onTestFailure(ITestResult result) {
		String filepath=null;
	extentTest.get().fail(result.getThrowable());	
	try {
		driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e1 ) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		 filepath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromBase64String(result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
	extent.flush();
	}


	

}
