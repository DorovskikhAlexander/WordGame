package WordGame.BooksGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import WordGame.Constants;
import WordGame.WebBase;
import pageObject.GamePage;

public class BooksGamePlay extends WebBase {

	@Test
	public void startGameAndRestart() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.BOOKS);
		gamePage.loadGame();

		Assert.assertEquals(gamePage.currentName(), getData("name"));

		gamePage.restartGame();

		gamePage.clearName();
		gamePage.addName(getData("name2"));
		gamePage.chooseCategory(Constants.BOOKS);
		gamePage.loadGame();

		Assert.assertEquals(gamePage.currentName(), getData("name2"));

		driver.close();
	}

	@Test
	public void allCorrectLetters() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.BOOKS);
		gamePage.loadGame();

		gamePage.chooseALetter('C');
		gamePage.chooseALetter('R');
		gamePage.chooseALetter('I');
		gamePage.chooseALetter('M');
		gamePage.chooseALetter('E');
		gamePage.chooseALetter('A');
		gamePage.chooseALetter('N');
		gamePage.chooseALetter('D');
		gamePage.chooseALetter('P');
		gamePage.chooseALetter('U');
		gamePage.chooseALetter('S');
		gamePage.chooseALetter('H');
		gamePage.chooseALetter('T');

		// Checking the number of guesses
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[0], "0");
		// Checking the number of attempts
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[1], "6");

		Assert.assertEquals(gamePage.currentWord(), Constants.ANSWER_BOOKS.toUpperCase());
		Assert.assertTrue(gamePage.isWinner());

		driver.close();
	}

	@Test
	public void allWrongLetters() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.BOOKS);
		gamePage.loadGame();
		// CrimeandPusht
		gamePage.chooseALetter('J');
		gamePage.chooseALetter('B');
		gamePage.chooseALetter('Z');
		gamePage.chooseALetter('Y');
		gamePage.chooseALetter('K');
		gamePage.chooseALetter('F');

		// Checking the number of guesses
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[0], "6");
		// Checking the number of attempts
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[1], "0");

		Assert.assertFalse(gamePage.isWinner());

		driver.close();
	}

	@Test
	public void mixedLettersWin() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.BOOKS);
		gamePage.loadGame();

		gamePage.chooseALetter('A');
		gamePage.chooseALetter('B');
		gamePage.chooseALetter('C');
		gamePage.chooseALetter('D');
		gamePage.chooseALetter('E');
		gamePage.chooseALetter('F');
		gamePage.chooseALetter('G');
		gamePage.chooseALetter('H');
		gamePage.chooseALetter('I');
		gamePage.chooseALetter('J');
		gamePage.chooseALetter('K');
		gamePage.chooseALetter('M');
		gamePage.chooseALetter('N');
		gamePage.chooseALetter('P');
		gamePage.chooseALetter('R');
		gamePage.chooseALetter('S');
		gamePage.chooseALetter('T');
		gamePage.chooseALetter('U');
		gamePage.chooseALetter('Y');
		gamePage.chooseALetter('Z');

		// Checking the number of guesses
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[0], "5");
		// Checking the number of attempts
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[1], "1");

		Assert.assertEquals(gamePage.currentWord(), Constants.ANSWER_BOOKS.toUpperCase());
		Assert.assertTrue(gamePage.isWinner());

		driver.close();
	}

	@Test
	public void mixedLettersLose() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.BOOKS);
		gamePage.loadGame();

		gamePage.chooseALetter('K');
		gamePage.chooseALetter('C');
		gamePage.chooseALetter('R');
		gamePage.chooseALetter('I');
		gamePage.chooseALetter('Z');
		gamePage.chooseALetter('M');
		gamePage.chooseALetter('E');
		gamePage.chooseALetter('A');
		gamePage.chooseALetter('N');
		gamePage.chooseALetter('D');
		gamePage.chooseALetter('F');
		gamePage.chooseALetter('P');
		gamePage.chooseALetter('U');
		gamePage.chooseALetter('S');
		gamePage.chooseALetter('H');
		gamePage.chooseALetter('J');
		gamePage.chooseALetter('B');
		gamePage.chooseALetter('Y');

		// Checking the number of guesses
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[0], "6");
		// Checking the number of attempts
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[1], "0");

		Assert.assertFalse(gamePage.isWinner());

		driver.close();
	}

}
