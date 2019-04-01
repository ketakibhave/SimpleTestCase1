package com.training.sanity.tests;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_081POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_081 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM login;
	private RTTC_081POM returnProduct;
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
		returnProduct = new RTTC_081POM(driver);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test(dataProvider="db-inputs1", dataProviderClass= LoginDataProviders.class)
	public void returnProduct(String orderid1, String customer, String first, String last, String emailid, String phone, String prodName, String modelName) throws InterruptedException, AWTException, IOException {
		//show options available under Sales
		returnProduct.showSalesOptionsAndNavigatetoReturns();
		//fetch database values and return a product for selected customer
		returnProduct.returnOrderWithDBValues(orderid1, customer, first, last, emailid, phone, prodName, modelName);
		//select the return order from Returns list
		returnProduct.selectFromReturnsList(orderid1);
		//capture alert message for deleting the record..
		Robot robot = new Robot();
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		ImageIO.write(screenFullImage, "png", new File("C:\\Automation training\\Project\\Screenshots\\RTTC81Delete.png"));
		//delete record
		returnProduct.deleteFromReturnsList(orderid1);
		//search and verify the deleted record
		String actualResult= returnProduct.verifyReturnOrderDelete(orderid1);
		String expectedResult= "No results!";
		Assert.assertEquals(actualResult, expectedResult);
	}
}