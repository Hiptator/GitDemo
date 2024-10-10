package ecoss.recourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getExtentReport() {
		// TODO Auto-generated method stub
		
		String reportLocation = System.getProperty("user.dir")+"//reports"+"//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportLocation);
		reporter.config().setReportName("Automation Testing");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ghazali");
		return extent;

	}

}
