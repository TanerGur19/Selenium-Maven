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

public class HomeworkWebtable {

	WebDriver driver;
	List<Integer> key = new ArrayList<>();
	List<String> value = new ArrayList<>();

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
	public void readTables() {
		Map<Integer, String> keyValue = new HashMap<>();

		//
		Select select = new Select(driver.findElement(By.id("recPerPage")));
		select.selectByIndex(select.getOptions().size() - 1);

		int count = Integer.parseInt(driver.findElement(By.id("total")).getText()) / 100; // +1

		for (int i = 1; i <= count; i++) { // if we say less than we need +1
			printData("reportTab");

			for (int j = 1; j < key.size(); j++) {
				keyValue.put(key.get(j), value.get(j));
			}
			driver.findElement(By.id("showNext")).click();
		}

		Set<Entry<Integer, String>> entry = keyValue.entrySet();
		for (Entry<Integer, String> each : entry) {
			System.out.println(each);
		}

	}

	public void printData(String id) {

		int rowsCount = driver.findElements(By.xpath("//table[@id='" + id + "']/tbody/tr")).size();
		int colCount = driver.findElements(By.xpath("//table[@id='" + id + "']/thead/tr/th")).size();

		for (int rows = 1; rows <= rowsCount; rows++) {
			List<String> list = new ArrayList();

			for (int cols = 1; cols <= 1; cols++) {
				String xPath = "//table[@id='" + id + "']/tbody/tr[" + rows + "]/td[" + cols + "]";
				String data = driver.findElement(By.xpath(xPath)).getText();

				key.add(Integer.valueOf(data));
			}

			for (int cols = 2; cols <= colCount; cols++) {
				String xPath = "////table[@id='reportTab']/tbody/tr[" + rows + "]/td[" + cols + "]";
				String data = driver.findElement(By.xpath(xPath)).getText();
				String str = "";
				data += str;
				list.add(data);
			}
			value.addAll(list);

		}

	}
}
