package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_085POM;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_085 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM login;
	private RTTC_085POM filterOrders;
	private static Properties properties;
	
	@BeforeSuite
	public void setUp() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		login = new RTTC_051POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		login.loginAdmin("admin", "admin@123");
		filterOrders = new RTTC_085POM(driver);
		filterOrders.showSalesOptions();
		}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	// Admin logins the Retail website and adds a manufacturer and a product
	@Test(priority=1, dataProvider = "getExcelData", dataProviderClass = RTTC_085.class)
	public void filterOrdersInTable(String orderID, String orderStatus, String addedDate, String customer, String total, String modifiedDate) {
		boolean IDPresent= filterOrders.filterResults(orderID, orderStatus, addedDate, customer, total, modifiedDate);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_DOWN);
		ScreenShot ss= new ScreenShot(driver);
		ss.captureScreenShot("TC85");
		//verify that ID is present in the results even when search criteria didn't include orderID
		Assert.assertEquals(IDPresent, true);
	}
	@DataProvider
	public Object[][] getExcelData(){
		System.out.println("getExcel from 85");
		String fileName ="C:\\Automation training\\Project\\Testing2.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName, 3); 
	}
}
