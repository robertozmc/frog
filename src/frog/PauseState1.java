package frog;

import java.util.ArrayList;
import java.util.Date;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Klasa - stan, będąca menu pauzy dla drugiego poziomu gry 
 * @author Robert Kłódka
 */
public class PauseState1 extends BasicGameState {
	
	public PauseState1() {
		super();
	}

	/** Abstrakcyjna metoda inicjalizująca stan gry */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	/** Metoda aktualizująca logikę gry */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		if (gc.getInput().getMouseX() >= gc.getWidth()/2-LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseX() <= gc.getWidth()/2+LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseY() >= LoadData.menu.getHeight()/2+50 
				&& gc.getInput().getMouseY() <= LoadData.menu.getHeight()/2+120
				&& gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			gc.setMouseCursor(LoadData.cursor, 25, 25);
			sbg.enterState(11, new FadeOutTransition(), new FadeInTransition());
		} else if (gc.getInput().getMouseX() >= gc.getWidth()/2-LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseX() <= gc.getWidth()/2+LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseY() >= LoadData.menu.getHeight()/2+125 
				&& gc.getInput().getMouseY() <= LoadData.menu.getHeight()/2+200
				&& gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			gc.setMouseCursor(LoadData.cursor, 25, 25);
			LoadData.flies = new ArrayList<Circle>();
			LoadData.flies1 = new ArrayList<Circle>();
			LoadData.flies2 = new ArrayList<Circle>();
			LoadData.totalPoints = 0;
			LoadData.points1 = 0;
			LoadData.points2 = 0;
			LoadData.points3 = 0;
			LoadData.timePassed = 0;
			LoadData.level1Time = 60000;
			LoadData.level2Time = 60000;
			LoadData.level3Time = 60000;
			LoadData.level = 1;
			sbg.enterState(10, new FadeOutTransition(), new FadeInTransition());
		} else if (gc.getInput().getMouseX() >= gc.getWidth()/2-LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseX() <= gc.getWidth()/2+LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseY() >= LoadData.menu.getHeight()/2+215 
				&& gc.getInput().getMouseY() <= LoadData.menu.getHeight()/2+290
				&& gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		} else if (gc.getInput().getMouseX() >= gc.getWidth()/2-LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseX() <= gc.getWidth()/2+LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseY() >= LoadData.menu.getHeight()/2+300 
				&& gc.getInput().getMouseY() <= LoadData.menu.getHeight()/2+375
				&& gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		} else if (gc.getInput().getMouseX() >= gc.getWidth()/2-LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseX() <= gc.getWidth()/2+LoadData.menu.getWidth()/2
				&& gc.getInput().getMouseY() >= LoadData.menu.getHeight()/2+390 
				&& gc.getInput().getMouseY() <= LoadData.menu.getHeight()/2+455
				&& gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			LoadData.totalTime = System.currentTimeMillis()-LoadData.startTime;
			LoadData.date = new Date();
			LoadData.txtFile.println(LoadData.totalPoints + " ; " + LoadData.username + " ; " + LoadData.totalTime/1000
					+ " ; " + LoadData.totalBlowingTime/1000 + " ; " + LoadData.format.format(LoadData.date));
			LoadData.txtFile.close();
			gc.exit();
		}
	}
	
	/** Metoda renderująca ekran gry */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		LoadData.background.draw(0, 0, Color.gray);
		LoadData.logo.draw(gc.getWidth()/2-LoadData.logo.getWidth()/2, LoadData.logo.getHeight()/5);
		LoadData.pause.draw(gc.getWidth()/2-LoadData.pause.getWidth()/2, LoadData.pause.getHeight()/2);
	}

	/** Metoda pobierająca identyfikator stanu gry */
	@Override
	public int getID() {
		return 31;
	}
}
