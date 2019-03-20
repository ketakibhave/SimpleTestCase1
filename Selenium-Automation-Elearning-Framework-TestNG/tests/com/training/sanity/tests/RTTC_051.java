package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_051POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_051 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM createCustomersGroup;
	private static Properties properties;
	private String customerGrp="Ket1234";
	private ScreenShot sh;

	@BeforeSuite
	public void setUp() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		createCustomersGroup = new RTTC_051POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		createCustomersGroup.loginAdmin("admin", "admin@123");
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	//Admin logins the Retail website and navigates to Dashboard
	@Test(priority = 1)
	public void displayCustomersOptions() {
		boolean expectedOptions = true;
		boolean actualOptions = createCustomersGroup.displayCustomersOptions();
		assertEquals(actualOptions, expectedOptions);

	}
	//click on Customers link and navigate to Customer Groups page
		@Test(priority = 2)
		public void navigateToCustomerGroupsPage() {
			createCustomersGroup.navigateToCustomersGroupPage();

			String expectedPageTitle = "Customer Groups";
			String actualPageTitle = driver.getTitle();
			assertEquals(actualPageTitle, expectedPageTitle);

		}
	//click on Add New button and navigate to Add Customer Groups page
	@Test(priority = 3)
	public void navigateToCreateNewCustomerGroupPage() {
		String expectedPageTitle = "Add Customer Group";
		String actualPageTitle = createCustomersGroup.navigateToCreateNewCustomerGroupPage();
		assertEquals(actualPageTitle, expectedPageTitle);

	}
	//fill the mandatory fields and save the Customer Group
	@Test(priority = 5)
	public void createNewCustomerGroup() {
		
		String expectedPageTitle = "Success: You have modified customer groups!";
		String actualPageTitle = createCustomersGroup.addCustomerGroup(customerGrp);
		sh=new ScreenShot(driver);
		sh.captureScreenShot("TC051_CustomerGroupAdded");
		boolean actual=actualPageTitle.contains(expectedPageTitle);
		boolean expected=true;
		assertEquals(actual, expected);

	}
	//navigate to Customers page
	@Test(priority = 6)
	public void navigateToCustomerPage() {
		createCustomersGroup.navigateToCustomersPage();

		String expectedPageTitle = "Customers";
		String actualPageTitle = driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);

	}
	//click on Add New button and navigate to Add Customer page
	@Test(priority = 7)
	public void navigateToAddCustomerPage() {
		
		String expectedPageTitle = "Add Customer";
		String actualPageTitle = createCustomersGroup.navigateToAddCustomersPage();
		assertEquals(actualPageTitle, expectedPageTitle);

	}
	//fill all mandatory fields and add a Customer
	@Test(priority = 8)
	public void addNewCustomer() {
		
		String expectedPageTitle = "Success: You have modified customers!";
		String actualPageTitle = createCustomersGroup.addNewCustomer(customerGrp);
		boolean actual=actualPageTitle.contains(expectedPageTitle);
		boolean expected=true;
		assertEquals(actual, expected);
		sh.captureScreenShot("TC051_CustomerAdded");
	}
	
}
