package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.xml.internal.ws.resources.AddressingMessages;

public class RTTC_055POM {
private WebDriver driver;
	
	public RTTC_055POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//nav/ul/li[3]/a")
	WebElement catalogOptions;
	
	@FindBy(xpath = "//nav/ul/li[3]/ul/li[7]")
	WebElement manufacturersLink;
	
	@FindBy(xpath = "//nav/ul/li[3]/ul/li[2]/a")
	WebElement productsLink;
	
	@FindBy(xpath = "//div/div/a/i")
	WebElement clickAdd;
	
	@FindBy(id = "input-name")
	WebElement manufacturerName;
	
	@FindBy(xpath = "//div/button/i")
	WebElement saveManufacturer;
	
	@FindBy(xpath = "//div/div/a/i")
	WebElement clickAddProduct;
	
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
	
	@FindBy(id = "input-model")
	WebElement modelToSearch;
	
	@FindBy(id = "button-filter")
	WebElement filterIcon;
	
	@FindBy(xpath = "//table/tbody/tr/td[4]")
	WebElement searchResultModel;
	
	public void showCatalogOptions() {
		
		Actions act= new Actions(driver);
		act.moveToElement(catalogOptions).build().perform();
		int count=driver.findElements(By.xpath("//nav/ul/li[3]/ul/li")).size();
		System.out.println("Menu-Sale options=>");
		for(int i=1;i<=count;i++) {
			System.out.println(driver.findElement(By.xpath("//nav/ul/li[3]/ul/li["+i+"]/a")).getText());
		}
		manufacturersLink.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//waiting till page gets loaded
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("button-filter")));
		System.out.println("=================================");
	}
	public void createManufacturer(String name) {
		clickAdd.click();
		manufacturerName.sendKeys(name);
		saveManufacturer.click();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified manufacturers!";
		System.out.println("Success Message: "+expectedMsg+"Showed? "+successMsg);
		System.out.println("true? "+successMsg.contains(expectedMsg));
	}
	public void navigateToProductsAdd(String name, String productname) {
		Actions act= new Actions(driver);
		act.moveToElement(catalogOptions).build().perform();
		productsLink.click();
		clickAddProduct.click();
		productName.clear();
		productName.sendKeys("Finger Ring");
		metaTag.sendKeys("Finger Ring for ladies");
		dataTab.click();
		model.sendKeys(productname);
		price.clear();
		price.sendKeys("500");
		quantity.clear();
		quantity.sendKeys("56");
		linksTab.click();
		manufacturer.sendKeys(name);
		category.sendKeys("EARRINGS");
		submit.click();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified products!";
		System.out.println("Success Message: "+expectedMsg+" Showed= "+successMsg);
		System.out.println("true?"+successMsg.contains(expectedMsg));
	}
	public String searchAddedProduct(String modelName) {
		Actions act= new Actions(driver);
		act.moveToElement(catalogOptions).build().perform();
		productsLink.click();
		modelToSearch.sendKeys(modelName);
		filterIcon.click();
		System.out.println("model- "+searchResultModel.getText());
		return searchResultModel.getText();
	}
}
