package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import objects.InventoryPage;

public class LoginTest {

	private static WebDriver driver;

	@BeforeClass

	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	//@AfterClass

	public void closeDriver() {
		driver.quit();
	}

	@Test(priority = 1)

	public void loginTest() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		File f = new File("data.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			for (int i = 0; i < 2; i++) {
				Row row = sheet.getRow(i);
				Cell c0 = row.getCell(0);
				Cell c1 = row.getCell(1);

				String username = c0.toString();
				String password = c1.toString();

				driver.navigate().to(Home.URL);
				Home.usernameInput(driver, username);
				Home.passwordInput(driver, password);
				Home.clickLogin(driver);

				String currentUrl = driver.getCurrentUrl();

				sa.assertEquals(currentUrl, InventoryPage.URL);

				Cell c2 = row.createCell(2);

				if (currentUrl.equals(InventoryPage.URL)) {
					c2.setCellValue("pass");
				} else {

					c2.setCellValue("fail");

				}
				OutputStream os = new FileOutputStream(f);
				wb.write(os);

			}

			sa.assertAll();
			wb.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Test(priority = 2)

	public void itemSortingTest() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		InventoryPage.clickSortItems(driver);
		InventoryPage.sortItemsByPrice(driver);

		List<WebElement> list = driver.findElements(By.className("inventory_item_name"));
		
		list.get(0).click();
		
		String actualResult= driver.getCurrentUrl();
		String expectedResult = "https://www.saucedemo.com/inventory-item.html?id=2";

		Assert.assertEquals(actualResult, expectedResult);

	}

}
