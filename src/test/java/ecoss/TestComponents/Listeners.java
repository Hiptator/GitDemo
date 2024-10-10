package ecoss.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ecoss.recourses.ExtentReporterNG;

public class Listeners extends BasedTest implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getExtentReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName()); //Create Report when Test Start
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS,"Test is PASSED");
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); //Get Life to Driver
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String SSLocation = null;
		try {
			SSLocation = getScreenShot(result.getMethod().getMethodName()); //Call Screenshot Method to take SS
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(SSLocation, result.getMethod().getMethodName()); //Put SS into Report
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		extentTest.get().fail(result.getThrowable()); //Throw Error Log into Report
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); //Get Life to Driver
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String SSLocation = null;
		try {
			SSLocation = getScreenShot(result.getMethod().getMethodName()); //Call Screenshot Method to take SS
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(SSLocation, result.getMethod().getMethodName()); //Put SS into Report
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		
		extent.flush();
	}
	
	

}
