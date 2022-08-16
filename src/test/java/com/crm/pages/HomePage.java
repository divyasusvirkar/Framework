package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;


public class HomePage extends TestBase {
	
	
	//CommonMethods CommonMethods = new CommonMethods();
	
    //WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	
	
	
	
	
	// Initializing PageObjects
	
	// click On New Button On Customer Page
			public void clickOnNewButton() throws Exception {
				
				CommonMethods.mouseHover("newButton_XPATH");
				CommonMethods.highlightelement("newButton_XPATH");

			}
			//select Standard WorkFlow Process from layout
			public void clickOnStandardWorkFlow() throws Exception {
				CommonMethods.highlightelement("srcreationStandardWorkFLow_XPATH");
				CommonMethods.Click("srcreationStandardWorkFLow_XPATH");

			}
		
}
