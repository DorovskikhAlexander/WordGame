package WordGame.MoviesGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import WordGame.Constants;
import WordGame.WebBase;
import pageObject.GamePage;

public class MoviesGamePlay extends WebBase {

	@Test
	public void startGameAndRestart() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.MOVIES);
		gamePage.loadGame();

		Assert.assertEquals(gamePage.currentName(), getData("name"));

		gamePage.restartGame();

		gamePage.clearName();
		gamePage.addName(getData("name2"));
		gamePage.chooseCategory(Constants.MOVIES);
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
		gamePage.chooseCategory(Constants.MOVIES);
		gamePage.loadGame();

		gamePage.chooseALetter('T');
		gamePage.chooseALetter('H');
		gamePage.chooseALetter('E');
		gamePage.chooseALetter('M');
		gamePage.chooseALetter('A');
		gamePage.chooseALetter('T');
		gamePage.chooseALetter('R');
		gamePage.chooseALetter('I');
		gamePage.chooseALetter('X');

		// Checking the number of guesses
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[0], "0");
		// Checking the number of attempts
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[1], "6");

		Assert.assertEquals(gamePage.currentWord(), Constants.ANSWER_MOVIES.toUpperCase());
		Assert.assertTrue(gamePage.isWinner());

		driver.close();
	}

	@Test
	public void allWrongLetters() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.MOVIES);
		gamePage.loadGame();

		gamePage.chooseALetter('B');
		gamePage.chooseALetter('C');
		gamePage.chooseALetter('D');
		gamePage.chooseALetter('F');
		gamePage.chooseALetter('G');
		gamePage.chooseALetter('J');

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
		gamePage.chooseCategory(Constants.MOVIES);
		gamePage.loadGame();

		gamePage.chooseALetter('T');
		gamePage.chooseALetter('H');
		gamePage.chooseALetter('E');
		gamePage.chooseALetter('M');
		gamePage.chooseALetter('A');
		gamePage.chooseALetter('B');
		gamePage.chooseALetter('C');
		gamePage.chooseALetter('D');
		gamePage.chooseALetter('F');
		gamePage.chooseALetter('G');
		gamePage.chooseALetter('R');
		gamePage.chooseALetter('I');
		gamePage.chooseALetter('X');

		// Checking the number of guesses
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[0], "5");
		// Checking the number of attempts
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[1], "1");

		Assert.assertEquals(gamePage.currentWord(), Constants.ANSWER_MOVIES.toUpperCase());
		Assert.assertTrue(gamePage.isWinner());

		driver.close();
	}

	@Test
	public void mixedLettersLose() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.MOVIES);
		gamePage.loadGame();

		gamePage.chooseALetter('T');
		gamePage.chooseALetter('H');
		gamePage.chooseALetter('E');
		gamePage.chooseALetter('M');
		gamePage.chooseALetter('A');
		gamePage.chooseALetter('T');
		gamePage.chooseALetter('R');
		gamePage.chooseALetter('I');
		gamePage.chooseALetter('B');
		gamePage.chooseALetter('C');
		gamePage.chooseALetter('D');
		gamePage.chooseALetter('F');
		gamePage.chooseALetter('G');
		gamePage.chooseALetter('J');

		// Checking the number of guesses
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[0], "6");
		// Checking the number of attempts
		Assert.assertEquals(gamePage.currentNumberOfGuessesAttempts()[1], "0");

		Assert.assertFalse(gamePage.isWinner());

		driver.close();
	}

}
