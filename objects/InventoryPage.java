package objects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class InventoryPage {

	public static final String URL = "https://www.saucedemo.com/inventory.html";

	private static final String ITEM_SORT_BTN = "#header_container > div.header_secondary_container > div.right_component > span > select";

	private static final String PRICE_LOHI = "//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]";

	
	public static void clickSortItems(WebDriver driver) {

		driver.findElement(By.cssSelector(ITEM_SORT_BTN)).click();

	}

	public static void sortItemsByPrice(WebDriver driver) {

		driver.findElement(By.xpath(PRICE_LOHI)).click();

	}

}
