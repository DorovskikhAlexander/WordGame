package WordGame.AnimalsGame;

import org.testng.Assert;
import org.testng.annotations.Test;

import WordGame.Constants;
import WordGame.WebBase;
import pageObject.GamePage;

public class AnimalsGamePlay extends WebBase {

	@Test(enabled = false)
	public void startGameAndRestart() {
		driver = openThePage();

		Assert.assertTrue(isPageLoaded());
		GamePage gamePage = new GamePage(driver);

		gamePage.addName(getData("name"));
		gamePage.chooseCategory(Constants.ANIMALS);
		gamePage.loadGame();

		Assert.assertEquals(gamePage.currentName(), getData("name"));

		driver.close();
	}

}
