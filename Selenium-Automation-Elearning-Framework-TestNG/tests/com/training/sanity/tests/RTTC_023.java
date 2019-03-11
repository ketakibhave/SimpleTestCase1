package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.pom.RTTC_023POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_023 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_023POM filterTaxList;
	private static Properties properties;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	//Admin logins the Retail website and navigates to Dashboard
	@Test(priority=1)
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterTaxList=new RTTC_023POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		filterTaxList.sendUserName("admin");
		filterTaxList.sendPassword("admin@123");
		filterTaxList.clickLoginBtn();
		String expectedPageTitle="Dashboard";
		String actualPageTitle=driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display the SubOptions available under Reports Option
	@Test(priority=2)
	public void displayReportsOptions() throws InterruptedException {
		
		boolean expected=true;
		boolean actual=filterTaxList.displayReportsList();
		assertEquals(actual, expected);
	}
	//Display the SubOptions available under Sales Option
	@Test(priority=3)
	public void displaySalesOptions() {
		
		boolean expected=true;
		boolean actual=filterTaxList.displaySalesList();
		assertEquals(actual, expected);
	}
	//click on Tax link and navigate to Tax Report page
	@Test(priority=4)
	public void navigateToTaxPage() {
		
		String expectedPageTitle="Tax Report";
		String actualPageTitle=filterTaxList.navigateToTax();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display options available under GroupBy dropdown
	@Test(priority=5)
	public void displayGroupByListBox() {
		
		boolean expected=true;
		boolean actual=filterTaxList.displayGroupByListboxOptions();
		assertEquals(actual, expected);
	}
	//Filter the results using the option "week"
	@Test(priority=6)
	public void filterOrderDetails() {
		
		String expectedTitle="Total";
		String actualTitle=filterTaxList.filterListbyGroup();
		assertEquals(actualTitle, expectedTitle);
	}
	
}

