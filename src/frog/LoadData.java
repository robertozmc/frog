package frog;

import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Klasa przechowująca parametry gry oraz ładująca większość zasobów.
 * Główne zasoby gry to pliki graficzne oraz dźwiękowe.
 * @author Robert Kłódka
 */
public class LoadData extends BasicGameState {
	/** Obraz tła gry */
	protected static Image background = null;
	/** Obraz kursora w grze */
	protected static Image cursor = null;
	/** Obraz loga gry */
	protected static Image logo = null;
	/** Obraz opcji w menu głównym gry */
	protected static Image menu = null;
	/** Obraz opcji w menu pauzy gry */
	protected static Image pause = null;
	/** Obraz zasad gry w menu pomocy */
	protected static Image help = null;
	/** Obraz nagłówka w menu wyników gry */
	protected static Image lastScores = null;
	/** Obraz nagłówka na ekranie wyników po każdym etapie gry */
	protected static Image scores = null;
	/** Obraz żabki na ekranie gry */
	protected static Image frog = null;
	/** Obraz muchy z poziomu pierwszego */
	protected static Image fly = null;
	/** Obraz muchy z poziomu drugiego */
	protected static Image fly1 = null;
	/** Obraz muchy z poziomu trzeciego */
	protected static Image fly2 = null;
	/** Dźwięk mokrego języka pojawiający się przy wydmuchu powietrza/kliknięcia LPM */
	protected static Sound sound = null;
	/** Generator liczb pseudolosowych */
	protected static Random random = null;
	/** Kształt geometryczny - linia, będący językiem żaby */
	protected static Line tongue = null;
	/** Kształt geometryczny - koło umieszczone pod kursorem,
	 *  który służy do detekcji kolizji z latającymi muchami 
	 */
	protected static Circle mouseCircle = null;
	/** Lista kształtów geometrycznych - kół symulujących kształt muchy z poziomu pierwszego,
	 *  służących do detekcji kolizji z kursorem 
	 */
	protected static ArrayList<Circle> flies = null;
	/** Lista kształtów geometrycznych - kół symulujących kształt muchy z poziomu drugiego,
	 *  służących do detekcji kolizji z kursorem 
	 */
	protected static ArrayList<Circle> flies1 = null;
	/** Lista kształtów geometrycznych - kół symulujących kształt muchy z poziomu trzeciego,
	 *  służących do detekcji kolizji z kursorem 
	 */
	protected static ArrayList<Circle> flies2 = null;
	/** Czcionka podstawowa Calibri */
	protected static Font font;
	/** Bitmapowa czcionka Slick, wyświetlająca znaki Unicode z czcionki TrueType */
	protected static UnicodeFont uniFont = null;
	/** Rozmiar czcionki podstawowej */
	protected static int fontSize = 35;
	/** Szerokość pola graficznego gry */
	protected static int gameWidth = 1280;
	/** Wysokość pola graficznego gry */
	protected static int gameHeight = 1024;
	/** Liczba klatek na sekundę FPS */
	protected static int frameRate = 59;
	/** Pełny ekran/gra w okienku */
	protected static boolean fullscreen = false;
	/** Zmienna przechowująca czas trwania poziomu gry */
	protected static int timePassed = 0;
	/** Dekrementowany czas gry dla poziomu pierwszego */
	protected static int level1Time = 60000;
	/** Dekrementowany czas gry dla poziomu drugiego */
	protected static int level2Time = 60000;
	/** Dekrementowany czas gry dla poziomu trzeciego */
	protected static int level3Time = 60000;
	/** Suma punktów zdobytych we wszystkich etapach gry */
	protected static int totalPoints = 0;
	/** Punkty zdobyte w pierwszym etapie gry */
	protected static int points1 = 0;
	/** Punkty zdobyte w drugim etapie gry */
	protected static int points2 = 0;
	/** Punkty zdobyte w trzecim etapie gry */
	protected static int points3 = 0;
	/** Zmienna przechowująca aktualny numer poziomu gry */
	protected static int level;
	/** Promień koła, będącego symulacją kształtu latającej muchy */
	protected static int circleRadius = 50;
	/** Szerokość lini, będącej językiem żaby */
	protected static int lineWidth = 10;
	/** Synchronizacja pionowa */
	protected static boolean vsync = true;
	/** Nazwa użytkownika wprowadzana podczas logowania się do gry */
	protected static String username;
	/** Tekst widoczny na ekranie ostatnich wyników oraz pomocy:
	 *  "Naciśnij klawisz ESPAPE, aby wrócić do menu głównego."
	 */
	protected static String text = "Naciśnij klawisz ESPAPE, aby wrócić do menu głównego.";
	/** Tekst widoczny na ekranie wyników w etapie pierwszym i drugim:
	 *  "Naciśnij klawisz ENTER, aby przejść do kolejnego etapu." 
	 */
	protected static String text1 = "Naciśnij klawisz ENTER, aby przejść do kolejnego etapu.";
	/** Tekst widoczny na ekranie wyników w ostatnim etapie:
	 *  "Naciśnij klawisz ENTER, aby przejść do menu głównego."
	 */
	protected static String text2 = "Naciśnij klawisz ENTER, aby przejść do menu głównego.";
	/** Tekst widoczny na ekranach wyników: "Zdobyte punkty: " */
	protected static String scoreText = "Zdobyte punkty: ";
	/** Czas rozpoczęcia działania gry */
	protected static long startTime = 0;
	/** Całkowity czas działania gry */
	protected static long totalTime = 0;
	/** Czas rozpoczęcia dmuchania */
	protected static long startBlowingTime = 0;
	/** Całkowity czas dmuchania */
	protected static long totalBlowingTime = 0;
	/** Zapisuje sformatowaną reprezentację obiektów do pliku tekstowego */
	protected static PrintWriter txtFile;
	/** Data i godzina zapisu danych */
	protected static Date date;
	/** Format zapisu daty i godziny: RRRRMMDD_GGMMSS */
	protected static SimpleDateFormat format;
	/** Obrazy ikon gry  */
	protected static String[] icons = new String[] {"images/icon16.png", "images/icon32.png"};
	
	public LoadData() {
		super();
	}
	
	/** Metoda inicjalizująca zasoby i parametry gry */
	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		startTime = System.currentTimeMillis();
		gc.setDefaultMouseCursor();
		gc.setShowFPS(false);
		background = new Image("images/background.png");
		cursor = new Image("images/crosshair.png");
		logo = new Image("images/logo.png");
		menu = new Image("images/menu.png");
		pause = new Image("images/pause.png");
		help = new Image("images/help.png");
		lastScores = new Image("images/lastScores.png");
		scores = new Image("images/scores.png");
		frog = new Image("images/frog.png");
		fly = new Image("images/fly.png");
		fly1 = new Image("images/fly1.png");
		fly2 = new Image("images/fly2.png");
		sound = new Sound("sounds/tongue.ogg");
		font = new Font("Calibri", Font.BOLD, fontSize);
		uniFont = new UnicodeFont((font), fontSize, true, false);
		uniFont.addAsciiGlyphs();
		uniFont.addGlyphs(0x000, 0x017F);
		uniFont.getEffects().add(new ColorEffect());
		uniFont.loadGlyphs();
		mouseCircle = new Circle(0, 0, 0);
		flies = new ArrayList<Circle>();
		flies1 = new ArrayList<Circle>();
		flies2 = new ArrayList<Circle>();
		random = new Random();
		format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		try {
			txtFile = new PrintWriter(new FileWriter("files/scores.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/** Abstrakcyjna metoda aktualizująca logikę gry */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		
	}
	
	/** Abstrakcyjna metoda renderująca ekran gry */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

	}
	
	/** Metoda pobierająca identyfikator stanu gry */
	@Override
	public int getID() {
		return 5;
	}
}