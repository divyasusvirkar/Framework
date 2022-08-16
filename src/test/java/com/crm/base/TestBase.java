package com.crm.base;

//import java.io.File;
import java.io.FileInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
///import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.Status;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

import io.github.bonigarcia.wdm.WebDriverManager;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static  WebDriver driver;
	
	public static Properties prop;
	public static Properties ObjectRepository = new Properties();	
	
	public static Logger log = LoggerFactory.getLogger(TestBase.class);
	public TestBase() {
		prop = new Properties();
		ObjectRepository = new Properties();
		FileInputStream ip;
		try {
			
			ip= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\propertiesfile\\confiq.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
		
			ip= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\propertiesfile\\ObjectRepository.properties");
			ObjectRepository.load(ip);
		}
		catch(FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

  

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Browser launched");
			
		} else if (browserName.equals("FireFox")) {
			

			driver = new FirefoxDriver();
			log.info("Browser launched");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(CommonMethods.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(CommonMethods.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		log.info("URL : "+prop.getProperty("url"));
		CustomListener.testReport.get().log(Status.INFO,"URL  " + " : "+prop.getProperty("url"));
		 
	}
	
	
	
	@AfterTest
	public void teardown() {
		driver.quit();
		log.info("Browser closed");
	}

}
