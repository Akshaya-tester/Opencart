package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	//invoking constructor
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	//locators
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_FirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_LastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_confirm_password;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement privacy_policy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continue_btn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confmsg;
	
	//action methods for each locators
	
	public void setFirstName(String fname)
	{
		txt_FirstName.sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		txt_LastName.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	public void setTelephone(String Telephone)
	{
		txt_telephone.sendKeys(Telephone);
	}
	public void setPassword(String Password)
	{
		txt_password.sendKeys(Password);
	}
	public void setconfirmPassword(String Password)
	{
		txt_confirm_password.sendKeys(Password);
	}
	public void click_privacypolicy()
	{
		privacy_policy.click();
	}
	public void click_continue()
	{
		continue_btn.click();
		//continue_btn.submit();
	}
	public String confmsg()
	{
		try {
			return (confmsg.getText()); //Your account has been created value will be returned to testcase class.
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}


}
