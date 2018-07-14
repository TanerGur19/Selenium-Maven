package webelements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomeworkTable2 {

	WebDriver driver;
	List<Integer> key = new ArrayList<>();
	List<String> value = new ArrayList<>();
	Map<Integer, String> applicants = new HashMap<>();

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(
				"https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");
	}

	@Test
	public void readTable() {

		Select select = new Select(driver.findElement(By.id("recPerPage")));
		select.selectByIndex(select.getOptions().size() - 1);

		int count = Integer.parseInt(driver.findElement(By.xpath("//span[@id='total']")).getText());

		for (int i = 1; i <= count; i++) {

			printData();

			for (int j = 1; j < key.size(); j++) {

				applicants.put(key.get(j), value.get(j));
			}
			driver.findElement(By.id("showNext")).click();

		}

		Set<Entry<Integer, String>> entry = applicants.entrySet();
		for (Entry<Integer, String> each : entry) {

			System.out.println(each);

		}
	}

	public void printData() {

		int rowCount = driver.findElements(By.xpath("//table[@id='reportTab']/tbody/tr")).size();
		int colCount = driver.findElements(By.xpath("//table[@id='reportTab']/thead/tr/th")).size();

		for (int row = 1; row <= rowCount; row++) {
			
			String str="";
			
			for (int col = 1; col <= 1; col++) {

				String xpath = "//table[@id='reportTab']/tbody/tr[" + row + "]/td["+col+"]";
				String data = driver.findElement(By.xpath(xpath)).getText();

				key.add(Integer.parseInt(data));
			}
			for (int col = 2; col <= colCount; col++) {

				String xpath = "//table[@id='reportTab']/tbody/tr[" + row + "]/td["+col+"]";
				String data2 = driver.findElement(By.xpath(xpath)).getText();
				
				str += data2;
			}
		
			value.add(str);
		}
		for (int i = 0; i < key.size(); i++) {
			applicants.put(key.get(i), value.get(i));
			
	}
		
		
		
		
	}

}
