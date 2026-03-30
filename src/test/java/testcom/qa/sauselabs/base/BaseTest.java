package testcom.qa.sauselabs.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import testcom.qa.sauselabs.factory.DriverFactory;
import testcom.qa.sauselabs.pages.HomePage;
import testcom.qa.sauselabs.pages.LoginPage;

public class BaseTest {
	
	protected Properties prop;
	WebDriver driver;
	
	protected DriverFactory driverFactoryPage;
	protected LoginPage loginPage;
	protected HomePage homePage;
	
	public BaseTest()
	{
		driverFactoryPage=new DriverFactory();
		
		prop=driverFactoryPage.getProperties();
		driver= driverFactoryPage.initDriver(prop);
		loginPage=new LoginPage(driver);
		homePage=new HomePage(driver);
	}

}
