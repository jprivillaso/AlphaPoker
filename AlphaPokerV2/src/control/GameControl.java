package control;

import modelpoker.GamePoker;

public class GameControl {

	private GamePoker gamePoker;
	
	public GameControl() {
		gamePoker =  new GamePoker();
	}
	
	
	//getters
	public GamePoker getGamePoker() {
		return gamePoker;
	}
	
}
