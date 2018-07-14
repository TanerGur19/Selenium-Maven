package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobs {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();  //  we dont have to upload jar files
		
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().fullscreen();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		String url="https://dice.com";
		
		driver.get(url);
		
		String actualTitle= driver.getTitle();
		String expectedTitle= "Job Search for Technology Professionals | Dice.com";
		
		if (actualTitle.equals(expectedTitle)) {
			
			System.out.println("Step PASS. Dice home page successfully loaded");
			
		}else {
			
			System.out.println("Step FAIL. Dice home page did not successfully loaded");
			throw new RuntimeException("Step FAIL. Dice home page did not successfully loade");  //  so we dont need to test one more time
			
		}
		
		
		
		String keyword= "javascript developer";
		driver.findElement(By.id("search-field-keyword")).clear();

		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location= "07011";
		driver.findElement(By.id("search-field-location")).clear();

		driver.findElement(By.id("search-field-location")).sendKeys(location);

		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		
		System.out.println(count);
		
		// ensure count is more than 0
		
		int countResult= Integer.parseInt(count.replace(",", ""));
		
		if (countResult > 0) {
			
			System.out.println("Step PASS: Keyword : " + keyword + "search returned"
			
			+ countResult + "results in " + location);
		}
		
		driver.close();
		
		System.out.println("TEST COMPLETED  "+ LocalDateTime.now());
		
		
	}
	
	
	
	
}