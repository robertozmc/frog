package frog;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Klasa - stan, będąca menu wyników dla drugiego poziomu gry 
 * @author Robert Kłódka
 */
public class ScoreState2 extends BasicGameState {
	
	public ScoreState2() {
		super();
	}

	/** Abstrakcyjna metoda inicjalizująca stan gry */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	/** Metoda aktualizująca logikę gry */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			LoadData.level = 3;
			sbg.enterState(12, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	/** Metoda renderująca ekran gry */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		LoadData.background.draw(0, 0, Color.gray);
		LoadData.scores.draw(gc.getWidth()/2-LoadData.scores.getWidth()/2, LoadData.scores.getHeight()/4);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth(LoadData.scoreText + LoadData.points2)/2,
				gc.getHeight()/2-LoadData.uniFont.getHeight(LoadData.scoreText + LoadData.points2),
				LoadData.scoreText + LoadData.points2);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth(LoadData.text1)/2,
				gc.getHeight()-LoadData.uniFont.getHeight(LoadData.text1)*2, LoadData.text1, Color.white);
	}
	
	/** Metoda pobierająca identyfikator stanu gry */
	@Override
	public int getID() {
		return 21;
	}
}