package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.pom.RTTC_024POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_024 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_024POM filterShippingList;
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
	@Test(priority = 1)
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterShippingList = new RTTC_024POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		filterShippingList.sendUserName("admin");
		filterShippingList.sendPassword("admin@123");
		filterShippingList.clickLoginBtn();
		String expectedPageTitle = "Dashboard";
		String actualPageTitle = driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display the SubOptions available under Reports Option
	@Test(priority = 2)
	public void displayReportsOptions() throws InterruptedException {

		boolean expected = true;
		boolean actual = filterShippingList.displayReportsList();
		assertEquals(actual, expected);
	}
	//Display the SubOptions available under Sales Option
	@Test(priority = 3)
	public void displaySalesOptions() {

		boolean expected = true;
		boolean actual = filterShippingList.displaySalesList();
		assertEquals(actual, expected);
	}
	//click on Shipping link and navigate to Shipping Report page
	@Test(priority = 4)
	public void navigateToShippingPage() {

		String expectedPageTitle = "Shipping Report";
		String actualPageTitle = filterShippingList.navigateToShipping();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display options available under GroupBy dropdown
	@Test(priority = 5)
	public void displayGroupByListBox() {

		boolean expected = true;
		boolean actual = filterShippingList.displayGroupByListboxOptions();
		assertEquals(actual, expected);
	}
	//Filter the results using the option "week"
	@Test(priority = 6)
	public void filterOrderDetails() {

		String expectedTitle = "Total";
		String actualTitle = filterShippingList.filterListbyGroup();
		assertEquals(actualTitle, expectedTitle);
	}

}
