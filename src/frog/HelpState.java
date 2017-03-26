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
 * Klasa - stan, będąca menu pomocy gry 
 * @author Robert Kłódka
 */
public class HelpState extends BasicGameState {
	
	public HelpState() {
		super();
	}

	/** Abstrakcyjna metoda inicjalizująca stan gry */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	/** Metoda aktualizująca logikę gry */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	/** Metoda renderująca ekran gry */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		LoadData.background.draw(0, 0, Color.gray);
		LoadData.help.draw(0, 0);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth(LoadData.text)/2,
				gc.getHeight()-LoadData.uniFont.getHeight(LoadData.text)*2, LoadData.text, Color.white);
	}
	
	/** Metoda pobierająca identyfikator stanu gry */
	@Override
	public int getID() {
		return 2;
	}
}