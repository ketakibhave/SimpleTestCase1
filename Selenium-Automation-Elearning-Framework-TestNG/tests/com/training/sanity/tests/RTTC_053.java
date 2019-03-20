package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_053POM;
import com.training.pom.RTTC_054POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_053 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_053POM orderStatusChange;
	private static Properties properties;
	private RTTC_051POM login;
	private ScreenShot sh;
	private String name="Asmita";
	private String lastName;
	
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
		orderStatusChange = new RTTC_053POM(driver);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	// Admin logins the Retail website and navigates to Dashboard
	@Test(priority = 1)
	public void displayLatestOrdersSection() {
		
		boolean expectedOptions = true;
		boolean actualOptions = orderStatusChange.displayLatestOrdersSection();
		assertEquals(actualOptions, expectedOptions);
	}
	@Test(priority = 2)
	public void navigateToOrdersPage() {
		String expectedMessage = "Success: You have modified orders!";
		String actualMessage = orderStatusChange.ordersPage();
		System.out.println("Actual Message=> "+actualMessage);
		boolean actual=actualMessage.contains(expectedMessage);
		assertEquals(actual, true);
	}
	
}
