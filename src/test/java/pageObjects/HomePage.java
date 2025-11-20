package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	//constructor invoking
	public HomePage (WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//span[@class='caret']")
	WebElement click_my_account;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement click_register;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement click_Login;
	
	//action methods for each locator
	public void click_my_account()
	{
		click_my_account.click();
	}
	public void click_register()
	{
		click_register.click();
	}
	public void click_Login()
	{
		click_Login.click();
	}

}
