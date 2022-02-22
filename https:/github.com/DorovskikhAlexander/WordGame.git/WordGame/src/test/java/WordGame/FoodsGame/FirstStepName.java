package WordGame.FoodsGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import WordGame.Constants;
import WordGame.WebBase;
import pageObject.GamePage;

public class FirstStepName extends WebBase {

	@Test
	public void startGameWithARegularName() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.FOODS);
		gamePage.loadGame();

		Assert.assertEquals(gamePage.currentName(), getData("name"));
		driver.close();
	}

	@Test
	public void startGameWithAnEmptyName() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.clearName();
		gamePage.chooseCategory(Constants.FOODS);
		gamePage.loadGame();

		// Checking the number of guesses
		Assert.assertNotEquals(gamePage.currentNumberOfGuessesAttempts()[0], "6");

		driver.close();
	}

	@Test
	public void startGameWithA50CharName() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.clearName();
		gamePage.addName(getData("50name"));
		gamePage.chooseCategory(Constants.FOODS);
		gamePage.loadGame();

		Assert.assertEquals(gamePage.currentName(), getData("50name"));
		driver.close();
	}

	@Test
	public void startGameWithMoreThan50CharName() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.clearName();
		gamePage.addName(getData("morethen50name"));
		gamePage.chooseCategory(Constants.FOODS);
		gamePage.loadGame();

		Assert.assertEquals(gamePage.currentName(), getData("50name"));
		driver.close();
	}

}
