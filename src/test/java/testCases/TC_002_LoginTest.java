package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass  {
	//public WebDriver driver;
	
	@Test(groups={"sanity","master"})
	public void verify_Login() throws IOException
	{
		try {
		
		logger.info("***Started executing login test***");
		HomePage hp=new HomePage(driver);
		logger.info("Clicking my account");
		hp.click_my_account();
		logger.info("Clicking login");
		hp.click_Login();
		LoginPage lp=new LoginPage(driver);
		logger.info("Entering username");
		lp.username(p.getProperty("uname"));
		logger.info("Entering password");
		lp.password(p.getProperty("pwd"));
		logger.info("Validating login");
		lp.login();
		AccountPage ap=new AccountPage(driver);
		Boolean myact=ap.myact_display();
		Assert.assertEquals(myact, true,"Login failed");
		//Assert.assertTrue(myact);
		ap.logout_click();
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Login test execution finished");
		
	}

}
