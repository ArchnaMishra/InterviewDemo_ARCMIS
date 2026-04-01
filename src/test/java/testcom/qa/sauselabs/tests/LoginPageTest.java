package testcom.qa.sauselabs.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import testcom.qa.sauselabs.base.BaseTest;
import testcom.qa.sauselabs.constants.AppConstants;


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
	
	@Description("Verify valid login")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void verifyValidLogin()
	{
		homePage= loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
		Assert.assertTrue(homePage.getHomePageURL().contains(AppConstants.HOME_PAGE_FRACTION_URL));
	}
	
	@Description("Verify negative scenarios")
	@Test(dataProvider= "testCredentials")
	public void verifyInvalidLogin(String email, String password)
	{
		String currentURL=loginPage.invalidLogin(email,password);
		Assert.assertEquals(currentURL,AppConstants.LOGIN_PAGE_URL);
	}

}
