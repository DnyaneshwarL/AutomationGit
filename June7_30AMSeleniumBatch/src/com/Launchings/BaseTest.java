package com.Launchings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public static WebDriver driver;
	public static String projectpath = System.getProperty("user.dir");
	public static FileInputStream fis;
	public static Properties p;
	public static Properties mainProp;
	public static Properties childProd;
	
	public static void init() throws Exception 
	{
		fis = new FileInputStream(projectpath+"/data.properties");
		p = new Properties();
		p.load(fis);
		
		fis = new FileInputStream(projectpath+"/environment.properties");
		mainProp = new Properties();
		mainProp.load(fis);
		String e = mainProp.getProperty("env");
		System.out.println(e);
		
		fis = new FileInputStream(projectpath+"/"+e+".properties");
		childProd = new Properties();
		childProd.load(fis);
		String url = childProd.getProperty("amazonurl");
		System.out.println(url);
	}
	
	
	public static void launch(String browser)
	{
		if(p.getProperty(browser).equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver", "F:\\July Browser Drivers\\chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(p.getProperty(browser).equals("firefox"))
			{
				//System.setProperty("webdriver.gecko.driver", "F:\\July Browser Drivers\\geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}			
		}		
	public static void navigate(String url)
	{
		//driver.get(childProd.getProperty(url));
		driver.navigate().to(childProd.getProperty(url));
	}
}
