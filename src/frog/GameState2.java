package frog;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Klasa - stan, będąca trzecim poziomem gry 
 * @author Robert Kłódka
 */
public class GameState2 extends BasicGameState {
	
	public GameState2() {
		super();
	}
	
	/** Metoda obsługująca puszczenie przycisku myszy 
	 * @param button przycisk myszy
	 */
	@Override
	public void mouseReleased(int button, int x, int y) {
		if (button == Input.MOUSE_LEFT_BUTTON) {
			LoadData.totalBlowingTime += System.currentTimeMillis()-LoadData.startBlowingTime;
		}
	}

	/** Abstrakcyjna metoda inicjalizująca stan gry */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	/** Metoda aktualizująca logikę gry */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		LoadData.level3Time -= t;
		LoadData.timePassed += t;
		if (LoadData.timePassed > 2000 + LoadData.random.nextInt(3000)) {
			LoadData.timePassed = 0;
			LoadData.flies2.add(new Circle(gc.getWidth()+LoadData.circleRadius,
					LoadData.circleRadius+LoadData.random.nextInt(gc.getHeight()/3), LoadData.circleRadius));
		}
		
		for (Circle c : LoadData.flies2) {
			c.setCenterX(c.getCenterX()-(t/2));
		}
		
		for (int i = LoadData.flies2.size()-1; i >= 0; i--) {
			Circle c = LoadData.flies2.get(i);
			if (c.getCenterX() < -50) {
				LoadData.flies2.remove(i);
			} else if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && c.intersects(LoadData.mouseCircle)) {
				LoadData.flies2.remove(i);
				LoadData.points3 += 15;
			}
		}
		
		LoadData.mouseCircle.setCenterX(gc.getInput().getMouseX());
		LoadData.mouseCircle.setCenterY(gc.getInput().getMouseY());
		
		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			LoadData.startBlowingTime = System.currentTimeMillis();
			LoadData.sound.play();
		}
		
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			LoadData.frog = new Image("images/frogOn.png");
			LoadData.tongue = new Line(gc.getWidth()/2, gc.getHeight()/2+LoadData.frog.getHeight()/1.55f, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		} else {
			LoadData.frog = new Image("images/frog.png");
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_ESCAPE)) {
			gc.setDefaultMouseCursor();
			LoadData.totalPoints = LoadData.points1+LoadData.points2+LoadData.points3;
			sbg.enterState(32, new FadeOutTransition(), new FadeInTransition());
		} else if (LoadData.level3Time/1000 <= 0) {
			LoadData.totalPoints = LoadData.points1+LoadData.points2+LoadData.points3;
			sbg.enterState(22, new FadeOutTransition(), new FadeInTransition());
		}
		
	}
	
	/** Metoda renderująca ekran gry */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		LoadData.background.draw(0, 0);
		LoadData.frog.draw(gc.getWidth()/2-LoadData.frog.getWidth()/2, gc.getHeight()/2+LoadData.frog.getHeight()/5);
		g.setColor(Color.white);
		LoadData.uniFont.drawString(10, gc.getHeight()-100, "Czas: " + LoadData.level3Time/1000);
		LoadData.uniFont.drawString(10+LoadData.uniFont.getWidth("Czas: " + LoadData.level3Time/1000)*2, gc.getHeight()-100, "Punkty: " + LoadData.points3);
		LoadData.uniFont.drawString(10+LoadData.uniFont.getWidth("Poziom: " + LoadData.level)*3, gc.getHeight()-100, "Poziom: " + LoadData.level);
		g.setColor(Color.transparent);
		for (Circle c : LoadData.flies2) {
			g.fill(c);
			LoadData.fly2.draw(c.getCenterX()-50, c.getCenterY()-50);
		}
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			g.setColor(Color.pink);
			g.setLineWidth(LoadData.lineWidth);
			g.draw(LoadData.tongue);
		}
		g.fill(LoadData.mouseCircle);
	}

	/** Metoda pobierająca identyfikator stanu gry */
	@Override
	public int getID() {
		return 12;
	}
	
}
