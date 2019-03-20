package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_052POM {
	private WebDriver driver;
	boolean match = false;

	public RTTC_052POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;

	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void sendPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void clickLoginBtn() {
		this.loginBtn.click();
	}

	@FindBy(xpath = "//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-customer']/a[1]")
	private WebElement menuIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li")
	private List<WebElement> customerOptions;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[1]")
	private WebElement navigateToCustomersLink;

	@FindBy(css = "#input-name")
	private WebElement customerName;

	@FindBy(css = "#button-filter")
	private WebElement filterIcon;

	@FindBy(xpath = "//form[1]/div[1]/table[1]/tbody/tr/td")
	private List<WebElement> elements;

	@FindBy(xpath = "//td[@class='text-right']//a[@class='btn btn-primary']")
	private WebElement editIcon;

	@FindBy(css = "#input-firstname")
	private WebElement firstName;

	@FindBy(xpath = "//a[contains(text(),'Address 1')]")
	private WebElement addressTab;

	@FindBy(id = "input-postcode1")
	private WebElement postcode;

	@FindBy(xpath = "//a[@href='#tab-reward']")
	private WebElement rewardsTab;

	@FindBy(id = "input-reward-description")
	private WebElement rewardDescription;

	@FindBy(id = "input-points")
	private WebElement points;

	@FindBy(css = "#button-reward")
	private WebElement addRewards;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement successMessge;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li")
	private List <WebElement> linksCountElement;

	@FindBy(xpath = "//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-report']/a[1]")
	private WebElement reportMenuIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[3]/a[1]")
	private WebElement customersIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[3]/ul/li[5]")
	private WebElement rewardPointsIcon;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]")
	private WebElement customerToSearch;

	@FindBy(xpath = "//button[@id='button-filter']")
	private WebElement filterCustomerIcon;
	
	@FindBy(xpath = "//form[1]/div[1]/table[1]/tbody/tr/td")
	private List<WebElement> tableElements;

	@FindBy(xpath = "//table[1]/tbody[1]/tr[1]/td[5]")
	private WebElement rewardPointsInTable;

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

	public void navigateToCustomersPage() {
		Actions act1 = new Actions(this.driver);
		act1.moveToElement(this.menuIcon).build().perform();
		this.navigateToCustomersLink.click();
	}
	public String selectCustomerBySearch(String name) {
		this.customerName.sendKeys(name);
		this.filterIcon.click();
		int size = this.elements.size();
		if (size == 1)
			return driver.findElement(By.xpath("//form[1]/div[1]/table[1]/tbody/tr/td[1]")).getText();
		else
			return driver.findElement(By.xpath("//form[1]/div[1]/table[1]/tbody/tr/td[2]")).getText();
	}

	public String clickEditIcon() {
		editIcon.click();
		return driver.findElement(By.xpath("//h3[@class='panel-title']")).getText();
	}

	public String editFieldValues(String name) {
		firstName.clear();
		firstName.sendKeys(name);
		addressTab.click();
		postcode.clear();
		postcode.sendKeys("8796545");
		rewardsTab.click();
		rewardDescription.sendKeys("review");
		points.sendKeys("50");
		addRewards.click();
		saveIcon.click();
		return successMessge.getText();
	}
	public boolean displayReportsList() {

		Actions act1=new Actions(driver);
		act1.moveToElement(reportMenuIcon).build().perform();
		String reportsIconList[]= {"Sales", "Products","Customers","Marketing"};
			
		int linksCount=linksCountElement.size();
		boolean match=true;
		System.out.println("Reports===> ");
		for(int i=1;i<=linksCount;i++) {
			match= reportsIconList[i-1].equals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li["+i+"]")).getText());
			System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li["+i+"]")).getText());
			if(!match)
			{
				System.out.println("Match?? "+match);
				System.out.println("=========================");
				return match;
			}
		}
		System.out.println("=========================");
		return match;
	}

	public boolean displayReportCustomersList() {
		String salesOptionList[]= {"Customers Online","Customer Activity","Customer Searches","Orders","Reward Points", "Credit"};
		int salesCount=driver.findElements(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[3]/ul/li")).size();
		//System.out.println("Sales links count== "+salesCount);

		//WebElement salesIcon=driver.findElement(By.linkText("Sales"));
		customersIcon.click();
		boolean match=true;
		System.out.println("Reports>Customers===>");
		for(int i=1;i<=salesCount;i++) {
			match= salesOptionList[i-1].equals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[3]/ul[1]/li["+i+"]/a[1]")).getAttribute("text"));
			System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[3]/ul[1]/li["+i+"]/a[1]")).getAttribute("text"));
			if(!match)
			{
				System.out.println("Match?? "+match);
				System.out.println("=========================");
				return match;
			}
		}
		System.out.println("=========================");
		return match;


	}

	public String navigateToRewardPoints() {
		this.rewardPointsIcon.click();
		return driver.getTitle();
	}

	public String selectCustomerToSearchRewardPoints(String expectedCustomerName) {
		customerToSearch.sendKeys(expectedCustomerName);
		filterCustomerIcon.click();
		return driver.findElement(By.xpath("//table[1]/tbody[1]/tr[1]/td[1]")).getText();
	}

	public String confirmRewardPoints() {
		
		return this.rewardPointsInTable.getText();
	}
}
