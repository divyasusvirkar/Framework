package com.crm.pages;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;



public class RegistrationPage extends TestBase{
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	
	//PageMethods--starts
	
      //create SR for Standard Work Flow Process

		public void createSRStandardWorkFLow(String servicerequest_sheetname ) throws Exception {
			
		
			//Select Lodgement Team
			CommonMethods.Click("lodgementTeamSearchicon_XPATH");
			
			CommonMethods.sendkeys("inputSearchTextBox_XPATH", servicerequest_sheetname, "LodgementUserTeam", 1);
			String LodgementUserTeamext=ExcelUtils.getCellData(servicerequest_sheetname, "LodgementUserTeam", 1);
		
			CommonMethods.Click("filterarrowbutton_XPATH");
			
			try {
			WebElement elementtext = driver.findElement(By.xpath("//div[contains(text(),'" + LodgementUserTeamext + "')]"));
			Thread.sleep(2000);
			elementtext.click();
			}
			catch(StaleElementReferenceException e) {
				WebElement elementtext = driver.findElement(By.xpath("//div[contains(text(),'" + LodgementUserTeamext + "')]"));
				Thread.sleep(2000);
				elementtext.click();
			}
			Thread.sleep(2000);
			
			
			//Select Product
			CommonMethods.Click("selectproductarrowbutton_XPATH");
			String product=ExcelUtils.getCellData(servicerequest_sheetname, "Product", 1);
			WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + product + "')]"));
			element.click();
			
			//Select accountCardDetailSearchParatameter
			CommonMethods.selectByText("accountCardDetailSearchParatameterDropdown_XPATH",servicerequest_sheetname,"AccountCardDetailSearchParameter",1);
			
			
			CommonMethods.scrollDown(400);
			//Enter Function
			CommonMethods.sendkeys("function_XPATH", servicerequest_sheetname, "Function", 1);
			String Functiontext=ExcelUtils.getCellData(servicerequest_sheetname, "Function", 1);
			CommonMethods.PickerSelect(Functiontext);
			Thread.sleep(2000);
			
			//Enter SubFunction
			
			CommonMethods.sendkeys("subFunction_XPATH", servicerequest_sheetname, "SubFunction", 1);
			String SubFunctiontext=ExcelUtils.getCellData(servicerequest_sheetname, "SubFunction", 1);
			CommonMethods.PickerSelect(SubFunctiontext);
			Thread.sleep(2000);
			
			//Enter SubSubFunction
		    
		     CommonMethods.sendkeys("subsubFunction_XPATH", servicerequest_sheetname, "SubSubFunction", 1);
			String SubSubFunctiontext=ExcelUtils.getCellData(servicerequest_sheetname, "SubSubFunction", 1);
			CommonMethods.PickerSelect(SubSubFunctiontext);
			Thread.sleep(2000);
			
			//Select Nature of query
			 CommonMethods.sendkeys("NatureOfQuery_XPATH", servicerequest_sheetname, "NatureofQuery", 1); 
			String NatureOfQueryText=ExcelUtils.getCellData(servicerequest_sheetname, "NatureofQuery", 1);
			CommonMethods.PickerSelect(NatureOfQueryText);
			Thread.sleep(2000);
			CommonMethods.scrollDown(200);
			
			//Enter Lodgement Remarks
			 
			  CommonMethods.sendkeys("remarkfield_XPATH", servicerequest_sheetname, "LodgementRemarks", 1); 
			  Thread.sleep(2000);
			 

			  // Click on save and proceed button
			  CommonMethods.Click("saveandProceedButton_XPATH");
				 Thread.sleep(20000);
				 String SRNumbertext= CommonMethods.getElementText("SRNumber_XPATH");
				 ExcelUtils.writeToExcel(servicerequest_sheetname, 1, 9, SRNumbertext);
				 ScreenShot.takeScreenShot("SR Creation");
				
				
		} 
		

}
