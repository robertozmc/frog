package frog;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Główna klasa gry, w której tworzone jest okno gry
 * @author Robert Kłódka
 */
public class Main {
	
	/** Kontener gry, który wyświetla grę jako samoistną aplikację */
	public static AppGameContainer app;

	/** Metoda uruchamiająca grę 
	 * @param args lista argumentów
	 */
	public static void main(String[] args) {
		try {
			app = new AppGameContainer(new Game("Żabka"));
			app.setAlwaysRender(true);
			app.setTargetFrameRate(LoadData.frameRate);
			app.setVSync(LoadData.vsync);
			app.setDisplayMode(LoadData.gameWidth, LoadData.gameHeight, LoadData.fullscreen);
			app.setIcons(LoadData.icons);
			app.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
