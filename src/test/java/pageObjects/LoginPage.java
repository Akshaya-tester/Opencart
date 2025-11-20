package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//constructor invoking
public class LoginPage extends BasePage{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
//locators
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement login_btn;
	
	//action methods
	public void username(String uname)
	{
		txt_email.sendKeys(uname);
	}
	public void password(String pwd)
	{
		txt_password.sendKeys(pwd);
	}
	public void login()
	{
		login_btn.click();
	}
	

}
