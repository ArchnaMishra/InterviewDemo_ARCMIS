package testcom.qa.sauselabs.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testcom.qa.sauselabs.constants.AppConstants;

import testcom.qa.sauselabs.base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	@DataProvider
	public Object[][] testCredentials()
	{
		return new Object[][] {
			{"locked_out_user","secret_sauce"},
			{"no_password",""},
			{"","secret_sauce"}
		};
	}
	
	@Test
	public void verifyValidLogin()
	{
		homePage= loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
		Assert.assertTrue(homePage.getHomePageURL().contains(AppConstants.HOME_PAGE_FRACTION_URL));
	}
	
	@Test(dataProvider= "testCredentials")
	public void verifyInvalidLogin(String email, String password)
	{
		String currentURL=loginPage.invalidLogin(email,password);
		Assert.assertEquals(currentURL,AppConstants.LOGIN_PAGE_URL);
	}

}
