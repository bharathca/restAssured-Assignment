package lib.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporter {
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest svcTest;
	public ExtentTest testSuite, test;
	public String testCaseName, testDescription, nodes, authors;
	
	/**
	 * This method will start the report by specifying the path of the html, and configure the html document title, reportname and theme
	 */
	public void startReport() {
		reporter = new ExtentSparkReporter("./reports/result.html");
		reporter.config().setDocumentTitle("JSONPlaceholder");
		reporter.config().setReportName("JSONPlaceholder Execution Report");
		reporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);		
	}

	/**
	 * This method will start the test case
	 * @param testCaseName - Test Case Details to be shown in the html
	 * @param testDescription - Test Description to be shown in the html
	 * @return ExtentTest 
	 */
	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		testSuite = extent.createTest(testCaseName, testDescription);		
		return testSuite;
	}

	/**
	 * This method will start the test case
	 * @param nodes - Node to be shown for the API resource
	 * @return ExtentTest 
	 */
	public ExtentTest startTestModule(String nodes) {
		test = testSuite.createNode(nodes);
		return test;
	}

	/**
	 * This method will end the report for the generation of the html
	 */
	public void endReport() {
		extent.flush();
	}


	/**
	 * This method will start the test case
	 * @param desc - Description for the reporting test step
	 * @param status - Pass/Fail/Warning/Info
	 */
	public static void reportRequest(String desc, String status) {

		if(status.equalsIgnoreCase("PASS")) {
			svcTest.pass(desc);		
		}else if(status.equalsIgnoreCase("FAIL")) {
			svcTest.fail(desc);
			throw new RuntimeException();
		}else if(status.equalsIgnoreCase("WARNING")) {
			svcTest.warning(desc);		
		}else {
			svcTest.info(desc);
		}	
	}


}

