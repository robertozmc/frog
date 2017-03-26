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
 * Klasa - stan, będąca menu ostatnich wyników gry 
 * @author Robert Kłódka
 */
public class ScoreState extends BasicGameState {
	
	public ScoreState() {
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
		LoadData.lastScores.draw(gc.getWidth()/2-LoadData.lastScores.getWidth()/2, LoadData.lastScores.getHeight()/2);
		g.setColor(Color.white);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth("Punkty zdobyte w pierwszym etapie: " + LoadData.points1)/2,
				gc.getHeight()/2-LoadData.uniFont.getHeight("Punkty zdobyte w pierwszym etapie: " + LoadData.points1),
				"Punkty zdobyte w pierwszym etapie: " + LoadData.points1);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth("Punkty zdobyte w drugim etapie: " + LoadData.points2)/2,
				gc.getHeight()/2, "Punkty zdobyte w drugim etapie: " + LoadData.points2);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth("Punkty zdobyte w trzecim etapie: " + LoadData.points3)/2,
				gc.getHeight()/2+LoadData.uniFont.getHeight("Punkty zdobyte w trzecim etapie: " + LoadData.points3),
				"Punkty zdobyte w trzecim etapie: " + LoadData.points3);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth("Suma punktów za wszystkie poziomy wynosi :" + LoadData.totalPoints)/2,
				gc.getHeight()/2+LoadData.uniFont.getHeight("Suma punktów za wszystkie poziomy wynosi :" + LoadData.totalPoints)*2, "Suma punktów za wszystkie poziomy wynosi: " + LoadData.totalPoints);
		LoadData.uniFont.drawString(gc.getWidth()/2-LoadData.uniFont.getWidth(LoadData.text)/2,
				gc.getHeight()-LoadData.uniFont.getHeight(LoadData.text)*2, LoadData.text);
	}
	
	/** Metoda pobierająca identyfikator stanu gry */
	@Override
	public int getID() {
		return 3;
	}
}