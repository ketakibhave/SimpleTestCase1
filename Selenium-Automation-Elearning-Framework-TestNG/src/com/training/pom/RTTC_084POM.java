package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RTTC_084POM {
	private WebDriver driver;
	public RTTC_084POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//nav/ul/li[3]/a")
	WebElement catalogOptions;

	@FindBy(xpath = "//nav/ul/li[3]/ul/li[2]/a")
	WebElement productsLink;
	
	@FindBy(xpath = "//div/div/a/i")
	WebElement clickAddProduct;
//Product details add-	
	@FindBy(id = "input-name1")
	WebElement productName;
	
	@FindBy(id = "input-meta-title1")
	WebElement metaTag;
	
	@FindBy(linkText = "Data")
	WebElement dataTab;
	
	@FindBy(id = "input-model")
	WebElement model;
	
	@FindBy(id = "input-price")
	WebElement price;
	
	@FindBy(id = "input-quantity")
	WebElement quantity;
	
	@FindBy(linkText = "Links")
	WebElement linksTab;
	
	@FindBy(id = "input-manufacturer")
	WebElement manufacturer;
	
	@FindBy(id = "input-category")
	WebElement category;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;
	
	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md']")
	WebElement logoutIcon;
//User page--	
	@FindBy(xpath = "//span[contains(text(),'Shop')]")
	WebElement shopIcon;
	
	@FindBy(xpath = "//span[contains(text(),'Earrings')]")
	WebElement navigateToList;
	
	@FindBy(css = "#search_button")
	WebElement searchBtn;
	
	@FindBy(css = "#filter_keyword")
	WebElement searchText;
	
	@FindBy(linkText = "MRF BAT")
	WebElement elementFound;

	public void showCatalogOptions() {

		Actions act= new Actions(driver);
		act.moveToElement(catalogOptions).build().perform();
		int count=driver.findElements(By.xpath("//nav/ul/li[3]/ul/li")).size();
		System.out.println("Menu-Category options=>");
		for(int i=1;i<=count;i++) {
			System.out.println(driver.findElement(By.xpath("//nav/ul/li[3]/ul/li["+i+"]/a")).getText());
		}
	}
	public boolean addProduct(String productname) {
		Actions act= new Actions(driver);
		act.moveToElement(catalogOptions).build().perform();
		productsLink.click();
		clickAddProduct.click();
		productName.clear();
		productName.sendKeys(productname);
		metaTag.sendKeys("Cricket Bat");
		dataTab.click();
		model.sendKeys("SKU-012");
		price.clear();
		price.sendKeys("500");
		quantity.clear();
		quantity.sendKeys("50");
		linksTab.click();
		category.sendKeys("Sports");
		submit.click();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified products!";
		boolean actualMsg= successMsg.contains(expectedMsg);
		logoutIcon.click();
		return actualMsg;
	}
	public String loginUserAndCheckProduct(String productName) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		
		Actions act=new Actions(driver);
		act.moveToElement(shopIcon).build().perform();
		navigateToList.click();
		
		act.moveToElement(searchBtn).click(searchText).sendKeys(productName).click(searchBtn).build().perform();
		driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_DOWN);
		wait.until(ExpectedConditions.visibilityOf(elementFound));
		return elementFound.getText();
		
		
	}
}
