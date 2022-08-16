package com.crm.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
//import java.time.Duration;
//import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
//import org.apache.log4j.Logger;
//import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
//import org.testng.Assert;
import org.slf4j.Logger;
import com.aventstack.extentreports.Status;
import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import java.time.Duration;
//import org.slf4j.Logger;



public class CommonMethods extends TestBase {
	public static long PAGE_LOAD_TIMEOUT=70;
	public static long IMPLICIT_WAIT=80;
	public static Logger log = LoggerFactory.getLogger(CommonMethods.class);
	
	 public  static JavascriptExecutor js;
	public static String parentwindow;
	public static WebElement element;
	public static Actions a ;
	public static WebDriverWait wait ;

	// to scroll down the page by pixel values as Y-coordiante
	public static void scrollDown(int y) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + y + ")");
	}

	

	// To scroll down the page at the bottom of page.
	public static void scrollAtBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// Return the complete height of body (page)
	}


	
	


	//Maximize Window
	public static void maximizeWindow() {

		driver.manage().window().maximize();
	}
	// Switch to window

	public static void switchwindow() {

		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			parentwindow = driver.getWindowHandle();
			//System.out.println(parentwindow);

			for (String handle : driver.getWindowHandles()) {
				// System.out.println(handle);
				if (!parentwindow.equalsIgnoreCase(handle)) {
					//System.out.println(handle);
					driver.switchTo().window(handle);
				}
			}

		} catch (Exception e) {
             e.getMessage();
		}

	}

	// switch to parent window

	public static void switchtoparentwindow() {

		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.switchTo().window(parentwindow);

		}

		catch (Exception e) {
			 e.getMessage();
		}
	}
	
	
	// Generate Random Mobile Number 10 digit

	public static String generateRandomMobileNumber() {
		Random random = new Random();
		//int randomInt = random.nextInt(1000000000);
		String id=String.format("%09d",random.nextInt(10000));
		String mobilenumber = String.valueOf(id);

		String s1 = "9";
		String actualmobnum = s1.concat(mobilenumber);
		return actualmobnum;
	}
   
	
	//generate Random Customer Id 9 digit
	public static String generateRandomCustomerID() {
		Random random = new Random();
		//int randomInt = random.nextInt(1000000000);
		String id=String.format("%09d",random.nextInt(10000));
		String customerid = String.valueOf(id);
		return customerid;

	}
	
	
	//generate Random Number
	
	public static int generateRandomNumber() {
		Random random = new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;
		
	}
	
	
	//generate Random Email
	
	public static String generateRandomEmail() {
		//int num=generateRandomNumber();
		Random random = new Random();
		//int randomInt = random.nextInt(1000000000);
		String id=String.format("%04d",random.nextInt(10000));
		String emailid = String.valueOf(id);
		String email= "test"+emailid+"@gmail.com";
		
		
		return email;
	}
	
	
	
	//generate random PAN Number
	
	public static String generatePANNumber() {
		Random random = new Random();
		String id=String.format("%04d",random.nextInt(10000));
		//int randomNum = random.nextInt(1000);
		//String pannumber= "ASDUY"+id+"Z";
		String pannumber= "CJJPS"+id+"Z";
		return pannumber;
	}
	//file upload
	
	public static void fileupload(String filepath) {
		try{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Robot robo=new Robot();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			//element.click();
			Thread.sleep(3000);
			StringSelection path= new StringSelection(filepath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
			
			robo.setAutoDelay(3000);
			
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			Thread.sleep(3000);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			log.info("File Uploaded successfully ");
			CustomListener.extentInfo("File Uploaded Successfully ","");
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void PickerSelect( String value) throws IOException, InterruptedException {
       
		String data = value;
      
		try {

			element = driver.findElement(By.xpath("//td[contains(text(),'" + data + "')]"));
            System.out.println(data);
			element.click();
			//.info("Selected " + data);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	public static boolean isTestRunnable(String testName) throws IOException
	{
		
		String sheetName="testsuite";
	int rows =ExcelUtils.getRowCount(sheetName);
	//System.out.println("No of rows : "+rows + " and test name = "+testName);

	for(int rNum=1; rNum<=rows; rNum++){

	String testCase = ExcelUtils.getCellData(sheetName, "TC Name", rNum);

	if(testCase.equalsIgnoreCase(testName)){

	String runmode = ExcelUtils.getCellData(sheetName, "RunMode", rNum);

	if(runmode.equalsIgnoreCase("Yes"))
	{
		initialization();
		return true;
	}
	else
		return false;
}
}
return false;
}


	
	
	public static void Click(WebElement element) 
	{
		try 
		{
			ExplicitWait(element);
			element.click();
			//log.info("Clicked Sucessfully on "+element.getText());
			//TestListeners.extentInfo("Clicked Sucessfully on "+element.getText());
		} catch (Exception e) 
		{
			log.error("Unable to click on "+element.getText() +" due to "+e.getMessage());
		}
	}

	
	
	public static void ExplicitWait(WebElement element) throws InterruptedException
	{
		wait= new WebDriverWait(driver,70);
		//wait=new WebDriverWait();
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	
	
	public static void sendkeys(WebElement element, String SheetName, String ColName, int rowNum,String msg) throws IOException
	{
		try {
			Click(element);
			element.clear();
			Thread.sleep(1000);
			element.sendKeys(ExcelUtils.getCellData(SheetName,ColName,rowNum));
			String text=ExcelUtils.getCellData(SheetName,ColName,rowNum);
			System.out.println(msg+" : "+text);
			System.out.println();
			log.info("Data Sucessfully entered on "+element.getAttribute("placeholder")+" = "+ExcelUtils.getCellData(SheetName,ColName,rowNum));
			//CustomListener.extentInfo("Data Sucessfully entered on "+element.getAttribute("placeholder")+" = "+TestUtil.getCellData(SheetName,ColName,rowNum));
			CustomListener.testReport.get().log(Status.INFO,"Data entered on " + element.getAttribute("placeholder")+ " = "+ExcelUtils.getCellData(SheetName,ColName,rowNum));
		}
		catch(Exception e)
		{
			log.error("Data Not Sucessfully entered on "+element.getAttribute("placeholder")+" due to :"+e.getMessage());
		}
	}

	
	

	
	public static void selectByText(WebElement element, String sheetName, String colName, int rowNum,String msg) throws InterruptedException 
	{
		Thread.sleep(1000);
		Select sel = new Select(element);
		//Select sel = new Select(element);
		//sel.selectByVisibleText(text);
		try {
			//scrollByVisibilityofElement(element);
			ExplicitWait(element);
			sel.selectByVisibleText(ExcelUtils.getCellData(sheetName,colName, rowNum));
			String text=ExcelUtils.getCellData(sheetName,colName,rowNum);
			System.out.println(msg+" : "+text);
			System.out.println();
			//sel.getFirstSelectedOption();
			log.info(sel.getFirstSelectedOption().getText()+" : Data Sucessfully Selected from dropdown ");
			//CustomListener.extentInfo("Data Sucessfully entered on "+element.getAttribute("placeholder")+" = "+TestUtil.getCellData(sheetName,colName,rowNum));
			CustomListener.testReport.get().log(Status.INFO,msg + " : "+ExcelUtils.getCellData(sheetName,colName,rowNum));
		} catch (Exception e) {
			log.error("Not able to select from dropdown "+element);
		} 
	}
	
	
	//
	public static String getElementText(WebElement element,String msg) throws InterruptedException 
	{
		//ExplicitWait(element);
		String txtMsg = element.getText();
		//log.info("Text of Web element :" +txtMsg);
		System.out.println(msg+" : "+txtMsg);
		System.out.println();
		CustomListener.testReport.get().log(Status.INFO,msg + " : "+txtMsg);
		return txtMsg;
	}
	
	//generate random client ID
		public static String generateRandomclientID() {
			Random random = new Random();
			//int randomInt = random.nextInt(1000000000);
			String id=String.format("%010d",random.nextInt(10000));
			String clientid = String.valueOf(id);
			return clientid;

		}
		
		
		
		
		//*******************************Common utilities***************************
		//Explicit Wait
		public static void ExWait(String locator) throws Exception
		{
			wait= new WebDriverWait(driver,70);
			//wait= new WebDriverWait(driver,Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectRepository.getProperty(locator))));
		}
	
		
		
		//CLick Element
		public static void Click(String locator)
		{
			try {
				 ExWait(locator); 
				if (locator.endsWith("_XPATH")) {
					driver.findElement(By.xpath(ObjectRepository.getProperty(locator))).click();
				} else if (locator.endsWith("_ID")) {
					driver.findElement(By.id(ObjectRepository.getProperty(locator))).click();
				}
				log.info("Sucessfully clicked on "+locator);
				CustomListener.extentInfo("Sucessfully clicked on ",locator);

			} catch (Exception e) {
				log.error("Not Sucessfully clicked on "+locator+" due to :"+e.getMessage());
			}
		}
		//To enter values  (sendkeys)
				public static void sendkeys(String locator, String text) 
				{
					try 
					{
						 Click(locator); 
						if (locator.endsWith("_XPATH"))
						 {
							 driver.findElement(By.xpath(ObjectRepository.getProperty(locator))).clear();
							 driver.findElement(By.xpath(ObjectRepository.getProperty(locator))).sendKeys(text);
						 } 
						else if (locator.endsWith("_ID")) 
						{
							driver.findElement(By.id(ObjectRepository.getProperty(locator))).clear();	
							driver.findElement(By.id(ObjectRepository.getProperty(locator))).sendKeys(text);
						}
						log.info("Data sucessfully entered on "+locator+" = "+text);
						CustomListener.extentInfo("Data sucessfully entered on "+locator," = "+text);

					} catch (Exception e) {
						log.error("Data Not Sucessfully entered on "+locator+" due to :"+e.getMessage());
					}
				}
		
		//To enter values  (sendkeys)
		public static void sendkeys(String locator, String SheetName, String ColName, int rowNum) 
		{
			try 
			{
				 Click(locator); 
				if (locator.endsWith("_XPATH"))
				 {
					 driver.findElement(By.xpath(ObjectRepository.getProperty(locator))).clear();
					 driver.findElement(By.xpath(ObjectRepository.getProperty(locator))).sendKeys(ExcelUtils.getCellData(SheetName,ColName,rowNum));
				 } 
				else if (locator.endsWith("_ID")) 
				{
					driver.findElement(By.id(ObjectRepository.getProperty(locator))).clear();	
					driver.findElement(By.id(ObjectRepository.getProperty(locator))).sendKeys(ExcelUtils.getCellData(SheetName,ColName,rowNum));
				}
				log.info("Data sucessfully entered on "+locator+" = "+ExcelUtils.getCellData(SheetName,ColName,rowNum));
				CustomListener.extentInfo("Data sucessfully entered on "+locator," = "+ExcelUtils.getCellData(SheetName,ColName,rowNum));

			} catch (Exception e) {
				log.error("Data Not Sucessfully entered on "+locator+" due to :"+e.getMessage());
			}
		}
		
		//to get element text
		public static String getElementText(String locator) throws Exception 
		{
			 ExWait(locator);
			 String txtMsg = driver.findElement(By.xpath(ObjectRepository.getProperty(locator))).getText();
			 CustomListener.extentInfo("Data extracted on "+locator," = "+txtMsg);

			 return txtMsg;
		}
		
		
		
		//to get Element value
		public static String getElementValue(String locator) throws Exception
		{
			ExWait(locator);
			String elementValue = driver.findElement(By.xpath(ObjectRepository.getProperty(locator))).getAttribute("value");
			CustomListener.extentInfo("Data extracted on "+locator," = "+elementValue);
			//log.info("Value of WebElement :" +elementValue);
			return elementValue;

		}
		
		
		//To highlight selected webelement
		public static void highlightelement(String locator) throws Exception
		{
			ExWait(locator);
			js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));
			js.executeScript("arguments[0].style.border='4px solid yellow'", element);
		}
		
		
		//To scroll down the page by visibility of the element
		public static void scrollByVisibilityofElement(String locator) throws Exception
		{
			js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));
			js.executeScript("arguments[0].scrollIntoView()",element);
		}
		
		
		//To select values from dropdown by visible text
		public static void selectByText(String locator, String sheetName, String colName, int rowNum) throws Exception 
		{
			WebElement element = driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));
			Select sel = new Select(element);
			try {
				ExWait(locator);
				String text = ExcelUtils.getCellData(sheetName,colName, rowNum);
				sel.selectByVisibleText(text);
				log.info("Data = "+text+" Sucessfully Selected from dropdown "+locator);
			} catch (Exception e) {
				log.error("Not able to select from dropdown "+locator+ "due to " +e.getMessage());
			} 
		}
		
		
		//To select values from dropdown by its value
		public static void selectByValue(String locator, String sheetName, String colName, int rowNum) throws InterruptedException, EncryptedDocumentException, IOException 
		{
			try {
				WebElement element = driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));
				String value = ExcelUtils.getCellData(sheetName,colName, rowNum);

				Select sel=  new Select(element);
				ExWait(locator);
				sel.selectByValue(value);
				log.info("Data = "+value+" Sucessfully Selected from dropdown "+locator);
			} catch (Exception e) {
				log.error("Not able to select from dropdown "+locator+ "due to " +e.getMessage());
			}
		}
		
		//To select values from dropdown by its index value
		public static void selectByIndex(String locator, int index) throws Exception 
		{
			try {
				WebElement element = driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));
				Select sel=  new Select(element);
				ExWait(locator);
				sel.selectByIndex(index);
				log.info("Data = "+index+" Sucessfully Selected from dropdown "+locator);
			} catch (Exception e) {
				log.error("Not able to select from dropdown "+locator+ "due to " +e.getMessage());
			}
		}

		//To handle mouse hover actions
		public static void mouseHover(String locator)throws Exception 
		{
			try {
				a = new Actions(driver);
				ExWait(locator);
				highlightelement(locator);
				WebElement element = driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));

				a.moveToElement(element).perform();	
				log.debug("Mouse hover on "+locator);
			} catch (Exception e) {
				log.error("Unable to mouse hover due to "+e.getMessage());
			}
			
		}	
		
		// To handle mouse hover actions
		public static void mouseClick(String locator) throws Exception {
			try {
				a = new Actions(driver);
				ExWait(locator);
				highlightelement(locator);
				WebElement element = driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));

				a.moveToElement(element).click().perform();
				log.debug("Mouse Click on " + locator);
			} catch (Exception e) {
				log.error("Not able to Mouseclick due to " + e.getMessage());
			}

		}
		
		
		
		//
	}