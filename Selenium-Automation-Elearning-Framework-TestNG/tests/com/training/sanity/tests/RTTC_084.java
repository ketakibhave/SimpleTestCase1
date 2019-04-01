package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_084POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_084 {
	WebDriver driver;
	private String baseUrl, baseUrl1;
	private RTTC_051POM login;
	private RTTC_084POM productCheck;
	private static Properties properties;
	String productName="MRF BAT";

	@BeforeTest
	public void setUp() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		login = new RTTC_051POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		login.loginAdmin("admin", "admin@123");
		productCheck = new RTTC_084POM(driver);
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(priority=1)
	public void productAddTest() {
		productCheck.showCatalogOptions();
		boolean actualMsg= productCheck.addProduct(productName);
		Assert.assertEquals(actualMsg, true);
	}

	@Test(priority=2)
	public void productCheckfromUserLogin() throws InterruptedException {
		((JavascriptExecutor)driver).executeScript("window.open();");
		Set<String> windows2=driver.getWindowHandles();
		driver.switchTo().window((String)windows2.toArray()[1]); 

		baseUrl1 = properties.getProperty("baseURL1");
		driver.get(baseUrl1);

		String actualElement= productCheck.loginUserAndCheckProduct(productName);
		Assert.assertEquals(actualElement, productName);
	}
}
