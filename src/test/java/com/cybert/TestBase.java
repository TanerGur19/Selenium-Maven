package com.cybert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	//is a class that serves as a utility class for all tests extend testbase class. 
	//TestBase class contains common methods like open, close driver, get url,
	//wait utilities, browser utilities.
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		
	}

	
////	@AfterMethod
//	public void tearDown() {
//		driver.quit();
		
//	}
	
	//will switch to new tab based on title
	//will switch back to the original tab,
	//if the expected title does not exist.
	public void switchByTitle(String title) {
		
		String original= driver.getWindowHandle();
		
		for (String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if(driver.getTitle().equals(title)) {
				//do not do anything else and leave the method
				//since we are now in the right window
				return;
			}
		}
		driver.switchTo().window(original);
		
	}
	
	
	
}


	
	
	
	
	
	
	
	
	
  

