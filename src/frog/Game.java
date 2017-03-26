package frog;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Klasa zawierająca spis wszystkich stanów gry
 * @author Robert Kłódka
 */
public class Game extends StateBasedGame {
		
	public Game(String title) {
		super(title);
	}
	
	/** Metoda inicjalizująca listę stanów, z której zbudowana jest gra */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new LoginState());
		this.addState(new MenuState());
		this.addState(new GameState());
		this.addState(new GameState1());
		this.addState(new GameState2());
		this.addState(new HelpState());
		this.addState(new PauseState());
		this.addState(new PauseState1());
		this.addState(new PauseState2());
		this.addState(new ScoreState());
		this.addState(new ScoreState1());
		this.addState(new ScoreState2());
		this.addState(new ScoreState3());
		this.addState(new LoadData());
	}
}
