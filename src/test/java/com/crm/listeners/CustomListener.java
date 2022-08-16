package com.crm.listeners;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.crm.utilities.EmailReporting;
import com.crm.utilities.ExtentReporter;
import com.crm.utilities.ScreenShot;

import com.crm.base.TestBase;







public class CustomListener extends ScreenShot implements ITestListener,ISuiteListener{
	
	String SD = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());

	ExtentTest test;
	ExtentReports extent = ExtentReporter.getReportsObject();
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
	public static List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
	public static List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
	public static LocalDateTime startTime;
	public static LocalDateTime endTime;
	
	public void onTestStart(ITestResult result) {
		//System.out.println(result.getName()+" test case started");	
		//test = extent.createTest(result.getMethod().getMethodName()+" test case started");
		//testReport.set(test);
		
		
        String methodName = result.getMethod().getMethodName();
		
		log.info("Test Case_  " + methodName.toUpperCase() + " _Successfully Started");
		
		ExtentTest test = extent.createTest("     @TestCase : " + result.getMethod().getMethodName().toUpperCase());
		testReport.set(test);
		
	}

	public void onTestSuccess(ITestResult result) {
		
		
		//System.out.println("The name of the testcase passed is :"+result.getName());	
		//testReport.get().log(Status.PASS, result.getMethod().getMethodName()+" Passed");
		
		
		
         String methodName = result.getMethod().getMethodName();
		
		log.info("Test Case_  " + methodName.toUpperCase() + " _Successfully Passed");
		
		System.out.println("**********  " + methodName + "  _Get Passed  ***********************");
		
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		
		try {
			//TestUtil.takeSnapShot(methodName, "Pass");
			
			//TestUtil.takeScreenShot(methodName);
		} catch (Exception e) {}
		
		
		
		
		
	}

	public void onTestFailure(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		log.error(
				" _ " + methodName.toUpperCase() + " _ " + ": Get Failed" + "\n" + result.getThrowable().getMessage());

		System.out.println("**********  " + methodName + "  _Get Failed  ***********************");
		//System.out.println("Exception Message::  " + result.getThrowable().getMessage());

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		// String excepionMessage= result.getThrowable().getMessage();
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		
		
		
		
		//commented code
		
		
		
		//testReport.get().fail(result.getThrowable());
		
		//String methodName=result.getMethod().getMethodName();
		
		try{
			
			//commented code
			//testReport.get().addScreenCaptureFromPath(TestUtil.failScreenShot(result.getMethod().getMethodName()), result.getMethod().getMethodName());
			
			ScreenShot.failScreenShot(methodName);
			//testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					//MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.ScreenShotPath).build());
			testReport.get().addScreenCaptureFromPath(ScreenShot.failScreenShot(methodName));
			//failAshot(result.getInstanceName().trim());
			
		}
		//catch(Exception e) {
		//	System.out.println(e.getMessage());
		//}
		
		catch (IOException e) {

		}

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

		log.info("Test Case_  " + methodName.toUpperCase() + " _Is Skipped As In Runmode it's 'NO' ");

		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void onStart(ISuite arg0) {
		startTime =  LocalDateTime.now();
		PassScreenShot = "PassScreenShot_" + SD;
		FailedScreenShot = "FailedScreenShot_" + SD;

		currentDir = System.getProperty("user.dir")+"\\Screenshots";
		outPutFolder = currentDir + "\\Output_" + SD;
		ScreenShotFolder = outPutFolder + "\\" + PassScreenShot;
		FailedScreenShotFolder = outPutFolder + "\\" + FailedScreenShot;

		flOutput = new File(outPutFolder);
		flScreenShotFolder = new File(ScreenShotFolder);
		flFailedScreenShotFolder = new File(FailedScreenShotFolder);
		flOutput.mkdir();
		flScreenShotFolder.mkdir();
		flFailedScreenShotFolder.mkdir();

	}
	
	public static void extentInfo(String message,String name)
	{
		Markup m = MarkupHelper.createLabel(message, ExtentColor.BLUE);
		testReport.get().log(Status.INFO, m);
	}
	
	public static void extentError(String message)
	{
		Markup m = MarkupHelper.createLabel(message, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
	}
	
	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}
		
	}
	public void onFinish(ISuite arg0) {
		
		  endTime = LocalDateTime.now(); 
		  try { 
			  
		 EmailReporting.sendReportViaEmail(passedtests.size(), failedtests.size(),skippedtests.size(), startTime, endTime);
		 } catch (IOException e) 
		  { // Auto-generated catch block 
			  e.printStackTrace(); 
			  
		  }
		 
	}
	

}
