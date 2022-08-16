package com.crm.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;

import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;

import com.crm.utilities.CommonMethods;


public class TC_SRCreationTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;

	CommonMethods commonmethods;
	
	@Test
	
	public void toverifySRIsGettingCreated() throws Exception {

		if (!(CommonMethods.isTestRunnable("TC_SRCreationTest"))) {

			throw new SkipException("Skipping the test "
					+ "To Verfiy SR Is Getting Created".toUpperCase() + "as the Run mode is NO");

		}
		//Excel sheet path
		
		String servicerequest_sheetname ="srcreation";
        
        loginpage = new LoginPage(driver);
		
		// Login to admin page
		loginpage.Login("initiator", "acid_qa");
		
		homepage = new HomePage(driver);
		
		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);
		//click On Standard WorkFLow Link
		homepage.clickOnStandardWorkFlow();
		Thread.sleep(2000);
		registrationPage = new RegistrationPage(driver);
		
		//create Service Request
		registrationPage.createSRStandardWorkFLow(servicerequest_sheetname);
		
		
		//logout
		loginpage.Logout();
		

}
}