package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.ProductsPage;
import pages.WebOrdersLogInPage;
import pages.WebOrdersLogInPage;

public class WebOrderTests {
	WebDriver driver;
	WebOrdersLogInPage loginPage;
	AllOrdersPage allOrdersPage;
	ProductsPage productsPage;
	String userId = "Tester";
	String password = "test";
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLogInPage(driver);
	}
	
	@Test(description="Verify labels and tab links are displayed",priority=1)
	public void labelsVerication() {
		assertEquals(driver.getTitle(),"Web Orders Login","LoginPage is not displayed. Application is down.");
		/*  loginPage.userName.sendKeys(userId);       // this message above "LoginPage..." will be displayed if test fails.
			loginPage.password.sendKeys(password);
		    loginPage.loginButton.click();
		*/
		loginPage.login(userId, password);
		
		allOrdersPage = new AllOrdersPage(driver);
		assertTrue(allOrdersPage.webOrders.isDisplayed(),"Web Orders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(),"List Of All Orders label is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""),"Welcome, " + userId + "!");
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(),"viewAllOrders is not displayed");
		assertTrue(allOrdersPage.orderTab.isDisplayed(),"orderTab is not displayed");
	}
	
	
	
	@Test (description = "Verify default Products and prices")
	public void availableProducts()  {
		
		
		assertEquals(driver.getTitle(),"Web Orders Login","LoginPage is not displayed. Application is down.");
		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(driver);
		allOrdersPage.viewAllProducts.click();
		productsPage= new ProductsPage(driver);
		List<String> expProducts= Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts= new ArrayList<>();
		
		
	//	productsPage.productNames.forEach(elem ->actProducts.add(elem.getText()));
		// down here same result
		for (WebElement prod : productsPage.productNames) {
			actProducts.add(prod.getText());
		}
		
		assertEquals(actProducts, expProducts);
		
		for (WebElement row : productsPage.productRows) {
			
			
			
		System.out.println(row.getText());
		String[] prodData= row.getText().split(" ");
		switch(prodData[0]) {
		
		case "MyMoney":
			assertEquals(prodData[1], "$100");
			assertEquals(prodData[2], "8%");

				break;
		case "FamilyAlbum":
			assertEquals(prodData[1], "$80");
			assertEquals(prodData[2], "15%");
			    break;
		case "ScreenSaver":
			assertEquals(prodData[1], "$20");
			assertEquals(prodData[2], "10%");
			    break;
		}
		
		}
		
	}
	//logout after each test
	@AfterMethod
	public void logout() {
		allOrdersPage.logout();
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
		
	}
	
	
	
	
	
	
	
	
	
}