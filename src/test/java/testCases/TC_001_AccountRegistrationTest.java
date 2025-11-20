package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	//public WebDriver driver;
	
	@Test(groups={"regression","master"})
	public void verifyRegister()
	{
		try {

		logger.info("****Starting Account Registration Test****");
		HomePage hp=new HomePage(driver);
		hp.click_my_account();
		logger.info("Clicked on My Account");
		hp.click_register();
		logger.info("Clicked on Register");
		
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		logger.info("Setting customer values..");
		arp.setFirstName(randomeString().toUpperCase());
		arp.setLastName(randomeString().toUpperCase());
		arp.setEmail(randomeString()+"@gmail.com");
		arp.setTelephone(randomeNumber());
		String pass=randomePass();
		arp.setPassword(pass);
		arp.setconfirmPassword(pass);
		arp.click_privacypolicy();
		arp.click_continue();
		
		logger.info("Validating the confirmation message");
		String confirm_message=arp.confmsg();
		if(confirm_message.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirm_message, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Finished Account Registration Test");
	}
}
	
	
