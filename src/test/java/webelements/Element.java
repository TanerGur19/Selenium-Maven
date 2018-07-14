package webelements;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Element {

	WebDriver driver;
	
	@BeforeClass // runs once for all test
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();	
	}
	
	@Test
	public void WebElementExamples() {
		
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ)");
		
		WebElement email= driver.findElement(By.name("Email"));
		
		String value= email.getAttribute("value");
		String maxLength= email.getAttribute("maxlength");
		String type= email.getAttribute("type");
		String tag= email.getTagName();
		boolean b= email.isEnabled();
		
		
		System.out.println("value"+ value + "\n"+
		"maxlength: " + maxLength + "type:  "+ type + "tag: " + tag + "isEnabled: " + b);
	
		assertEquals(value, "youremail@mail.com");
		
		email.clear();
		email.sendKeys("another@mail.com");
		
		WebElement country = driver.findElement(By.id("Adress_Country"));
		Select selectCountry= new Select(country);  // Drop down menu countries use Select
		   //  We passed WebElement inside Select
		
	//	Select selectCountry2= new Select(driver.findElement(By.id("Adress_Country")));
		
		String d= selectCountry.getFirstSelectedOption().getText();
		System.out.println(d);
		selectCountry.selectByIndex(79);
		
		// lets to generate StaleElementExceptoion
		
		WebElement cSalary= driver.findElement(By.name("Number"));
		assertEquals(cSalary.isDisplayed(), true);
		
		driver.get("https://google.com");
		
		
	//	driver.findElement(By.xpath("//*[.=' Next ']")).click();
		
		
		cSalary.sendKeys("123456"); // we are asking Selenium to use this variable(cSalary) again but its no there anymore.
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
