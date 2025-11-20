package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseClass {
	public static  WebDriver driver;
	public Logger logger;//for logging
	public Properties p;//for reading properties file
	
	
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setUp (String os, String br) throws InterruptedException, IOException
	{
		//loading config.properties file
		
		FileReader file=new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		logger=LogManager.getLogger(this.getClass());//log4j2
		
		//selenium-grid section--remote 
		//OS setup
		
		  if(p.getProperty("execution_env").trim().equalsIgnoreCase("remote")) {
		  DesiredCapabilities cap=new DesiredCapabilities();
		  
		  if(os.equalsIgnoreCase("windows")) { cap.setPlatform(Platform.WIN11); } else
		  if(os.equalsIgnoreCase("mac")) { cap.setPlatform(Platform.MAC); } else {
		  System.out.println("No Matching OS found."); return; } //browser setup
		  switch(br.toLowerCase()) { case "chrome": cap.setBrowserName("chrome");break;
		  case "edge": cap.setBrowserName("edge");break; default:
		  System.out.println("No matching browser.");return; }
		  
		  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		  
		  }
		 
		
		
		if(p.getProperty("execution_env").trim().equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		case "edge": driver=new EdgeDriver();break;
		default: System.out.println("Invalid browser");return;
		}
		}
		
		
		driver.get(p.getProperty("appURL"));//reading url from config.properties file
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
	}
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	//here we used randomeString() user-defined method because, as we are testing the registration page,
	//we cannot enter the same e-mail addess multiple times. Which means if we hardcode the email and pass it to the object class,
	//then we can only run this testcase class once. Because if we run multiple times we get a warning message in the application UI.
	//so we are trying to create random string value for email id using this user-defined method. 
	//It uses RandomStringUtils method which is part of  org.apache.commons.lang3.RandomStringUtils which we have already
	//added in the dependencies ie; pom.xml page.The randomAlphabetic(number of characters in the string needed) will generate random string.

	public String randomeNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}//this method creates random number with randomNumeric()method. But return string value not int.
	
	public String randomePass()
	{
		String generatedAlphaNumericValue=RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphaNumericValue;
	}
	
	public String captureScreen(String tname)throws IOException
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname +"_"+timeStamp;
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}
}


