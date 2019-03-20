package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RTTC_051POM {
	private WebDriver driver;
	boolean match = false;

	public RTTC_051POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;
	public void loginAdmin(String userName, String password)
	{
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear();
		this.password.sendKeys(password);
		this.loginBtn.click();
	}

	@FindBy(xpath = "//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-customer']/a[1]")
	private WebElement menuIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li")
	private List<WebElement> customerOptions;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[1]")
	private WebElement navigateToCustomersLink;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[2]")
	private WebElement navigateToCustomerGroupsLink;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")
	private WebElement navigateToCreateNewGroupLink;
	
	@FindBy(xpath = "//input[@placeholder='Customer Group Name']")
	private WebElement customerGroupName;
	
	@FindBy(css = "#input-description1")
	private WebElement description;
	
	@FindBy(xpath = "//input[@value='1']")
	private WebElement approveNewCustomers;
	
	@FindBy(xpath = "//i[@class='fa fa-save']")
	private WebElement saveButton;
	
	@FindBy(css = "body:nth-child(2) div:nth-child(1) div:nth-child(3) div.container-fluid > div.alert.alert-success")
	private WebElement confirmGroupCreation;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")
	private WebElement addCustomerButton;
	
	@FindBy(id = "input-firstname")
	private WebElement firstName;
	
	@FindBy(id = "input-lastname")
	private WebElement lastName;
	
	@FindBy(id = "input-firstname1")
	private WebElement firstName1;
	
	@FindBy(id = "input-lastname1")
	private WebElement lastName1;
	
	@FindBy(id = "input-email")
	private WebElement emailid;
	
	@FindBy(id = "input-telephone")
	private WebElement telephone;
	
	@FindBy(id = "input-password")
	private WebElement newpassword;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmpassword;
	
	@FindBy(xpath = "//a[@onclick='addAddress();']")
	private WebElement addAddressIcon;
	
	@FindBy(id = "input-address-11")
	private WebElement address1;
	
	@FindBy(id="input-address-21")
	private WebElement address2;

	@FindBy(id="input-city1")
	private WebElement city;
		
	@FindBy(id = "input-postcode1")
	private WebElement postcode;
	
	@FindBy(xpath = "//i[@class='fa fa-save']")
	private WebElement saveCustomer;

	public boolean displayCustomersOptions() {

		Actions act1 = new Actions(this.driver);
		act1.moveToElement(this.menuIcon).build().perform();
		String customerIconList[] = { "Customers", "Customer Groups", "Custom Fields" };
		int count = customerOptions.size();
		System.out.println("List of links available under Customers==> ");
		for (int i = 1; i <= count; i++) {
			this.match = customerIconList[i - 1].equals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[" + i + "]")).getText());
			System.out.println(driver
					.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[" + i + "]")).getText());
			if (!match) {
				System.out.println("Match?? " + this.match);
				System.out.println("=========================");
				return match;
			}
		}
		System.out.println("=========================");
		return this.match;
	}

	public void navigateToCustomersGroupPage() {
		this.navigateToCustomerGroupsLink.click();
	}

	public String navigateToCreateNewCustomerGroupPage() {
		this.navigateToCreateNewGroupLink.click();
		return driver.findElement(By.xpath("//h3[@class='panel-title']")).getText();
	}

	public String addCustomerGroup(String groupName) {
		customerGroupName.sendKeys(groupName);
		description.sendKeys(groupName);
		approveNewCustomers.click();
		saveButton.click();
		return confirmGroupCreation.getText();
	}
	public void navigateToCustomersPage() {
		Actions act1 = new Actions(this.driver);
		act1.moveToElement(this.menuIcon).build().perform();
		this.navigateToCustomersLink.click();
	}
	public String navigateToAddCustomersPage() {
		addCustomerButton.click();
		return driver.findElement(By.xpath("//h3[@class='panel-title']")).getText();
	}

	public String addNewCustomer(String customerGrp) {
		Select customerGroup= new Select(driver.findElement(By.cssSelector("#input-customer-group")));
		customerGroup.selectByVisibleText(customerGrp);
		firstName.sendKeys("Mandar");
		lastName.sendKeys("P");
		emailid.sendKeys("manp@gmail.com");
		telephone.sendKeys("221270");
		password.sendKeys("welcome");
		confirmpassword.sendKeys("welcome");
		addAddressIcon.click();
		firstName1.sendKeys("Mandar");
		lastName1.sendKeys("P");
		address1.sendKeys("Airoli");
		address2.sendKeys("Navi Mumbai");
		city.sendKeys("Mumbai");
		postcode.sendKeys("400028");
		Select country=new Select(driver.findElement(By.xpath("//select[@id='input-country1']")));
		country.selectByVisibleText("India");
		Select regionState=new Select(driver.findElement(By.cssSelector("#input-zone1")));
		regionState.selectByVisibleText("Maharashtra");
		saveCustomer.click();
		
		return driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
	}

}
