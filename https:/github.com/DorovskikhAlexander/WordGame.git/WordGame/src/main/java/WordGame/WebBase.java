package WordGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebBase {

	public WebDriver driver;

	/*
	 * Initiates a driver based on a browser property in data.properties file
	 */

	public WebDriver initializeDriver() {

		String browser = getData("browser");

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equals("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	/*
	 * Captures data value from data.properties based on a key
	 */
	public static String getData(String key) {
		Properties property = new Properties();
		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/WordGame/data.properties");
			property.load(fs);
		} catch (IOException e) {
			System.out.println(e);
			System.err.println("Property file is not available");
		}
		return property.getProperty(key);

	}

	/*
	 * Open url page
	 */
	public WebDriver openThePage() {
		String url = getData("url");
		driver = initializeDriver();
		driver.get(url);
		return driver;
	}

	/*
	 * Returns true if the page is loaded
	 */
	public boolean isPageLoaded() {

		boolean isLoaded = false;
		WebElement el = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(getData("bodyId"))));

		String PageTitle = driver.getTitle();
		String expectedTitle = getData("gamepagetitle");
		if (PageTitle.equals(expectedTitle)) {
			isLoaded = true;

		}
		return isLoaded;
	}

}
