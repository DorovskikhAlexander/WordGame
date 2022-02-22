package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import WordGame.WebBase;

public class GamePage extends WebBase {

	public WebDriver driver;

	public GamePage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Add name to the Name field on first step
	 */
	public void addName(String Name) {
		driver.findElement(By.cssSelector(getData("nameCSS"))).sendKeys(Name);
	}

	/*
	 * Removes everything from the Name field on first step
	 */
	public void clearName() {
		driver.findElement(By.cssSelector(getData("nameCSS"))).clear();
	}

	/*
	 * Chose a game category on first step
	 */
	public void chooseCategory(String type) {
		WebElement category = driver.findElement(By.cssSelector(getData("categaryCSS")));
		Select dropdown = new Select(category);
		dropdown.selectByVisibleText(type);
	}

	/*
	 * Clicks Load Game button on first step
	 */
	public void loadGame() {
		driver.findElement(By.cssSelector(getData("loadButtonCSS"))).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(getData("restartButtonCSS"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(getData("restartButtonCSS"))));
	}

	/*
	 * Returns name of the player
	 */
	public String currentName() {
		By textCategory = By.cssSelector(getData("welcomeTextCSS"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(textCategory));
		return driver.findElement(textCategory).getText().split("WordGame ")[1].split("!")[0].trim();

	}

	/*
	 * Returns current number of guesses and attempts
	 */
	public String[] currentNumberOfGuessesAttempts() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(getData("attenptsTextXPATH"))));
		String attemptsText = driver.findElement(By.xpath(getData("attenptsTextXPATH"))).getText();
		String guesses = attemptsText.split("missed ")[1].split(" guess")[0].trim();
		String attempts = attemptsText.split("you have ")[1].split(" attempt")[0].trim();
		String[] rt = { guesses, attempts };
		return rt;
	}

	/*
	 * Click a certain letter button
	 */
	public void chooseALetter(char c) {
		List<WebElement> letters = driver.findElements(By.cssSelector(getData("alphabetCSS")));
		for (int i = 0; i < letters.size(); i++) {
			WebElement letter = letters.get(i).findElement(By.cssSelector(getData("letterLevelCSS")));
			if (c == letter.getText().toCharArray()[0]) {
				letter.click();
				break;
			}

		}
	}

	/*
	 * Returns current word
	 */
	public String currentWord() {
		List<WebElement> letters = driver.findElements(By.cssSelector(getData("wordTextCSS")));
		String currentWord = "";
		for (int i = 0; i < letters.size(); i++) {

			if (letters.get(i).findElements(By.cssSelector(getData("letterLevelInWordCSS"))).size() > 0) {
				WebElement letter = letters.get(i).findElement(By.cssSelector(getData("letterLevelInWordCSS")));
				currentWord = currentWord + letter.getText();
			} else {
				currentWord = currentWord + " ";
			}

		}
		return currentWord;
	}

	/*
	 * Returns true if can find the phrase "Congratulations you just won"
	 */
	public boolean isWinner() {
		boolean rt = false;
		if (driver.findElements(By.xpath(getData("winnerTextXPATH"))).size() > 0) {
			rt = true;
		}
		return rt;
	}

	/*
	 * Click Restart Game button
	 */
	public void restartGame() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(getData("restartButtonCSS"))));
		driver.findElement(By.cssSelector(getData("restartButtonCSS"))).click();
	}

}
