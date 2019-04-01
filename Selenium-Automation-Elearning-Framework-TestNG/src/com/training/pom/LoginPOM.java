package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {
	private WebDriver driver; 
	
	public LoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
//original names--
	@FindBy(xpath="//input[@id='input-username']")
	private WebElement username;

	@FindBy(xpath="//input[@id='input-password']")
	  private WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	    private WebElement loginButton ; 
	/* 
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement username;

	@FindBy(xpath="//input[@id='input-password']")
	  private WebElement password;

	@FindBy(xpath="//input[@value='Login']")
	    private WebElement loginButton ;
	
	
	
	@FindBy(xpath="//input[@placeholder='Username']")
	private WebElement username;

	@FindBy(xpath="//input[@id='password']")
	  private WebElement password;

	@FindBy(xpath="//button[@id='form-login_submitAuth']")
	    private WebElement loginButton ;
	*/
	public void sendUserName(String userName) {
		this.username.clear();
		this.username.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginButton.click(); 
	}
}
