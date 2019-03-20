package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RTTC_054POM {
	private WebDriver driver;
	public WebDriverWait wait;

	public RTTC_054POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//nav/ul/li[6]/a")
	WebElement salesOptions;

	@FindBy(xpath = "//nav/ul/li[6]/ul/li[1]")
	WebElement ordersLink;

	@FindBy(xpath = "//div[@class='pull-right']//a[@class='btn btn-primary']")
	WebElement addNewButton;
//Customer details page--
	@FindBy(css = "#input-firstname")
	WebElement firstName;

	@FindBy(css = "#input-lastname")
	WebElement lastName;

	@FindBy(css = "#input-email")
	WebElement email;

	@FindBy(css = "#input-telephone")
	WebElement telephone;

	@FindBy(css = "#button-customer")
	WebElement continueBtn;
//Products page--
	@FindBy(css = "#input-product")
	WebElement product;
	
	@FindBy(xpath = "//fieldset/div[1]/div[1]/ul[1]/li[3]/a")
	WebElement productOptionToChoose;
	
	@FindBy(xpath = "//form/div/div[2]/div/table/tbody/tr[1]/td[2]")
	WebElement selectedProduct;

	@FindBy(css = "#input-quantity")
	WebElement quantity;

	@FindBy(css = "#button-product-add")
	WebElement addProductBtn;

	@FindBy(css = "#button-cart")
	WebElement btnContinue;
//Payment details page--
	@FindBy(css = "#input-payment-firstname")
	WebElement paymentFirstName;

	@FindBy(css = "#input-payment-lastname")
	WebElement paymentLastName;

	@FindBy(css = "#input-payment-address-1")
	WebElement paymentAddress1;

	@FindBy(css = "#input-payment-address-2")
	WebElement paymentAddress2;

	@FindBy(css = "#input-payment-city")
	WebElement paymentCity;

	@FindBy(css = "#input-payment-postcode")
	WebElement paymentPostcode;

	@FindBy(css = "#button-payment-address")
	WebElement continueToShipping;
//Shipping page--
	@FindBy(css = "#input-shipping-firstname")
	WebElement shippingFirstName;
	
	@FindBy(css = "#input-shipping-lastname")
	WebElement shippingLastName;

	@FindBy(css = "#input-shipping-address-1")
	WebElement shippingAddress1;
	
	@FindBy(css = "#input-shipping-city")
	WebElement shippingCity;

	@FindBy(css = "#input-shipping-postcode")
	WebElement shippingPostcode;

	@FindBy(css = "#button-shipping-address")
	WebElement continueToTotal;
//Total page--
	@FindBy(css = "#button-save")
	WebElement save;
	
	@FindBy(css = "#input-shipping-method")
	WebElement shippingMethod;
	
	@FindBy(xpath = "//option[@value='free.free']")
	WebElement selectShippingMethod;

	public void displaySaleOptionsNavigate() {
		Actions act= new Actions(driver);
		act.moveToElement(salesOptions).build().perform();
		int count=driver.findElements(By.xpath("//nav/ul/li[6]/ul/li")).size();
		System.out.println("Menu-Sale options=>");
		for(int i=1;i<=count;i++) {
			System.out.println(driver.findElement(By.xpath("//nav/ul/li[6]/ul/li["+i+"]/a")).getText());
		}
		ordersLink.click();
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//waiting till page gets loaded
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("button-filter")));
		System.out.println("=================================");
	}
	public void fillCustomerDetails() {
		addNewButton.click();
		firstName.sendKeys("alka");
		lastName.sendKeys("b");
		email.sendKeys("alkab@gmail.com");
		telephone.sendKeys("222120");
		continueBtn.click();
		
	}
	public void fillProductDetails() throws InterruptedException {
		//Select product_dropdown = new Select(driver.findElement(By.xpath("//fieldset/div[1]/div[1]/ul[1]/li[3]")));
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.visibilityOf(product));
		Actions act1=new Actions(driver);
		act1.moveToElement(product).clickAndHold(product).moveToElement(productOptionToChoose).click().build().perform();
		quantity.clear();
		quantity.sendKeys("1");
		addProductBtn.click();
		System.out.println("Selected product== "+product.getAttribute("value")+" Model= "+selectedProduct.getText());
		btnContinue.click();
		
	}
	public void fillPaymentDetails() throws InterruptedException {
		Thread.sleep(1000);
		paymentFirstName.sendKeys("alka");
		paymentLastName.sendKeys("b");
		paymentAddress1.sendKeys("abc");
		paymentAddress2.sendKeys("mumbai");
		paymentCity.sendKeys("mumbai");
		Select country=new Select(driver.findElement(By.cssSelector("#input-payment-country")));
		country.selectByVisibleText("India");
		Select paymentZone=new Select(driver.findElement(By.cssSelector("#input-payment-zone")));
		paymentZone.selectByVisibleText("Maharashtra");
		continueToShipping.click();
	}
	public void fillShippingDetails() throws InterruptedException {
		Thread.sleep(1000);
		shippingFirstName.sendKeys("alka");
		shippingLastName.sendKeys("b");
		shippingAddress1.sendKeys("abc");
		shippingCity.sendKeys("mumbai");
		Select country=new Select(driver.findElement(By.cssSelector("#input-shipping-country")));
		country.selectByVisibleText("India");
		Select paymentZone=new Select(driver.findElement(By.cssSelector("#input-shipping-zone")));
		paymentZone.selectByVisibleText("Maharashtra");
		continueToTotal.click();
	}
	
	public boolean totalDetails() throws InterruptedException {
		Thread.sleep(1000);
		//Select shippingMethod=new Select(driver.findElement(By.id("input-shipping-method")));
		//shippingMethod.click();
		Actions act1=new Actions(driver);
		//act1.click(shippingMethod).moveToElement(selectShippingMethod).click().build().perform();
		act1.click(shippingMethod).sendKeys(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).click().build().perform();
		Select paymentMethod=new Select(driver.findElement(By.id("input-payment-method")));
		paymentMethod.selectByValue("cod");
		save.click();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified orders!";
		
		System.out.println("Success Message as expected? "+successMsg.contains(expectedMsg));
		
		return successMsg.contains(expectedMsg);
	}
}
