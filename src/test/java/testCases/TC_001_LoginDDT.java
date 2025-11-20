package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid--login success--test pass-logout
 * Data is valid--login failed--test fail
 * 
 * Data is invalid--login success--test fail--logout
 * Data is invalid--login fail--test pass
 */

public class TC_001_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven")//getting dataprovider from different class and from different package
	public void verify_LoginDDT(String email, String pwd, String exp_result)
	{
		try {
			
		
	logger.info("Started login DDTest");
	HomePage hp=new HomePage(driver);
	hp.click_my_account();
	hp.click_Login();
	
	LoginPage lp=new LoginPage(driver);
	lp.username(email);
	lp.password(pwd);
	lp.login();
	
	AccountPage ap=new AccountPage(driver);
	Boolean myact=ap.myact_display();
	
	/*-------MOST IMPORTANT-------------
	 * 1) Data is valid--login success--test pass-logout
	 *2) Data is valid--login failed--test fail
	 * 
	 * 3) Data is invalid--login success--test fail--logout
	 * 4) Data is invalid--login fail--test pass
	 */
	
	if(exp_result.equalsIgnoreCase("valid"))
	{
		if(myact==true)
		{
			ap.logout_click();
			Assert.assertTrue(true);
			
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	else
	{
		if(myact==true)
		{
			ap.logout_click();
			Assert.assertTrue(false);	
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
		
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		finally {
			logger.info("Finished login DDTest");
	
		}
	}
	
	
	
}
