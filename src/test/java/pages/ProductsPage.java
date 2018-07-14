package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

	// add constructor
	
	public ProductsPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);  // purpose is intialize all elements using in this driver
		
	}
	
	@FindBy(xpath="//table[@class='ProductsTable']/tbody/tr/td[1]")
	public List<WebElement> productNames;  // it can handle multiple webElements
	
	@FindBy (xpath="//table[@class='ProductsTable']/tbody/tr")
	public List<WebElement> productRows;
	
	
	
	
}
