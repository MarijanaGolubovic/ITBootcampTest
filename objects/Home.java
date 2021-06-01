package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home {

	/*
	 * Napisati program na programskom jeziku Java koji sluzi za testiranje sajta
	 * https://www.saucedemo.com/ Pokusati logovanje prvo sa nevalidnim, a potom sa
	 * validnim kredencijalima i proveriti da li se nakon toga korisnik nalazi na
	 * odgovarajucoj staranici. Na stranici https://www.saucedemo.com/inventory.html
	 * sortirati proizvode po ceni (od najnize ka najvisoj). Proveriti da li je
	 * sortiranje ispravno. Program pisati postujuci page object model. Koristiti
	 * Test NG za proveru ispravnosti testova. Kredencijale procitati iz datoteke
	 * data.xlsx. Resenje okaciti na GitHub nalog i svoj folder na google drive-u
	 * (napraviti folder pod nazivom kontrolni3)
	 */

	public static final String URL = "https://www.saucedemo.com/";

	private static final String USER_ID = "user-name";

	private static final String PASSWORD_ID = "password";

	private static final String LOGIN_BTN_ID = "login-button";

	public static void usernameInput(WebDriver driver, String input) {
		WebElement we = driver.findElement(By.id(USER_ID));
		we.sendKeys(input);
	}

	public static void passwordInput(WebDriver driver, String input) {
		WebElement we = driver.findElement(By.id(PASSWORD_ID));
		we.sendKeys(input);
	}

	public static void clickLogin(WebDriver driver) {
		driver.findElement(By.id(LOGIN_BTN_ID)).click();

	}

}
