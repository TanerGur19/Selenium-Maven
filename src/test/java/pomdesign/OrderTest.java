package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.OrderPage;
import pages.WebOrdersLogInPage;

public class OrderTest {
	WebDriver driver;
	WebOrdersLogInPage loginPage;
	AllOrdersPage allOrdersPage;
	OrderPage orderPage;
	String userId = "Tester";
	String password = "test";

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	// new 
	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLogInPage(driver);
	}
	

		
	@Test (description= "Storing data into the fields")
	public void storingData() {
		Assert.assertEquals(driver.getTitle(),"Web Orders Login", "Login page is not displayed.Application is down");
		loginPage.login(userId, password);
		orderPage= new OrderPage(driver);
		orderPage.order.click();
		
		String name= "Taner";
		
		new Select(orderPage.product).selectByVisibleText("FamilyAlbum");
		orderPage.quantity.clear();
		orderPage.quantity.sendKeys("150");
		orderPage.customername.sendKeys(name);
		orderPage.street.sendKeys("900 Jira ave");
		orderPage.city.sendKeys("Arlington");
		orderPage.zip.sendKeys("10019");
		orderPage.visaCard.click();;
		orderPage.cardNumber.sendKeys("123457557779");
		orderPage.expireDate.sendKeys("11/31");
		orderPage.process.click();
		
		allOrdersPage= new AllOrdersPage(driver);
		allOrdersPage.viewAllOrders.click();
		
		
		String actual= driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]")).getText();
		String expected= "Taner FamilyAlbum 150 07/12/2018 900 Jira ave Arlington   10019 Visa 123457557779 11/31";
		
		Assert.assertEquals(actual, expected);
		
		
//		List<WebElement> element= driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]"));
//	    List<String> actual= new ArrayList<>();
//
//		List<String> expected= new ArrayList<>();
//		
//		for(int i=1; i<actual.size(); i++) {
//			
//			expected.add(actual.get(i).getText());
//			System.out.println(actual.get(i).getText());
//		}
//		//	System.out.println(actual);
//		System.out.println(expected);
//		actual.remove(0);
//		Assert.assertEquals(actual, expected);
			
		
	

		
		
		
	}
	
	
	
	
	
	
	}
	
	
	
	
	


