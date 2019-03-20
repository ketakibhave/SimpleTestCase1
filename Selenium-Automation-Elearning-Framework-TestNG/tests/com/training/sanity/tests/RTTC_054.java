package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_054POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


public class RTTC_054 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM login;
	private RTTC_054POM placeOrder;
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
		placeOrder = new RTTC_054POM(driver);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	// Admin logins the Retail website and navigates to Dashboard
	@Test(priority = 1)
	public void displayOptionsNavigateToOrders() throws InterruptedException {
		placeOrder.displaySaleOptionsNavigate();
		placeOrder.fillCustomerDetails();
		placeOrder.fillProductDetails();
		placeOrder.fillPaymentDetails();
		placeOrder.fillShippingDetails();
		boolean actualMessage= placeOrder.totalDetails();
		Assert.assertEquals(actualMessage, true);
	}
}
