package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

import com.crm.utilities.CommonMethods;


public class LoginPage extends TestBase {
	
	//Constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	
	
	
	

	// Methods

	

	public void Login(String uname, String pwd) throws Exception {
		
		
		//Enter Username
		CommonMethods.sendkeys("Username_XPATH", uname);
		
		//Enter password
		CommonMethods.sendkeys("Password_XPATH", pwd);
		
		//click on login Button
		CommonMethods.Click("LoginButton_XPATH");
		
		
	}
	
	//logout
	public void Logout() throws Exception {
		CommonMethods.highlightelement("profileimage_XPATH");
		CommonMethods.Click("profileimage_XPATH");
		Thread.sleep(2000);
		CommonMethods.Click("LogoutButton_XPATH");
		//profileimage.click();
		//logoutbtn.click();

	}

}
