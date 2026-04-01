package testcom.qa.sauselabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import testcom.qa.sauselabs.utils.ElementUtil;

public class LoginPage {
	
	// WebDriver instance
	WebDriver driver;
	ElementUtil elUtil;
	
	// Page level Locators
	private By txtemail= By.id("user-name");
	private By txtPassword=By.id("password");
	private By btnSubmit=By.id("login-button");
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver= driver;
		elUtil=new ElementUtil(driver);
	}
	
	public String getLoginPageTitle()
	{
		String loginPageTitle= driver.getTitle();
		System.out.println("Login Page Title " + loginPageTitle);
		return loginPageTitle;		
	}
	
	
	public String getLoginPageURL()
	{
		String loginPageURL= driver.getCurrentUrl();
		System.out.println("Login Page URL " + loginPageURL);
		return loginPageURL;		
	}
	
	// Verify login operation
	public HomePage login(String email, String password)
	{
		elUtil.sendKeys(txtemail, email);
		elUtil.sendKeys(txtPassword, password);
		elUtil.findElement(btnSubmit).click();
		return new HomePage(driver);
	}
	
	public String invalidLogin(String email, String password)
	{
		elUtil.sendKeys(txtemail, email);
		elUtil.sendKeys(txtPassword, password);
		elUtil.findElement(btnSubmit).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getLoginPageURL();
	}
}
