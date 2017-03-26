package frog;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Klasa, będąca stanem gry, w którym następuje logowanie 
 * @author Robert Kłódka
 */
public class LoginState extends BasicGameState {
	/** Pole tekstowe do wprowadzania nazwy użytkownika */
	private TextField login;
	/** Tekst z informacją: "Wpisz swoje imię i naciśnij ENTER" widoczny na ekranie logowania */
	private String text;
	/** Czcionka podstawowa Calibri */
	protected static Font font;
	/** Bitmapowa czcionka Slick, wyświetlająca znaki Unicode z czcionki TrueType */
	protected static UnicodeFont uniFont = null;
	
	public LoginState() {
		super();
	}

	/** Metoda inicjalizująca stan gry */
	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		font = new Font("Calibri", Font.BOLD, LoadData.fontSize);
		uniFont = new UnicodeFont((font), LoadData.fontSize, true, false);
		uniFont.addAsciiGlyphs();
		uniFont.addGlyphs(0x000, 0x017F);
		uniFont.getEffects().add(new ColorEffect());
		uniFont.loadGlyphs();
		login = new TextField(gc, uniFont, gc.getWidth()/3, gc.getHeight()/2+LoadData.fontSize, gc.getWidth()/3, LoadData.fontSize);
		text = "Wpisz swoje imię i naciśnij ENTER";
	}

	/** Metoda aktualizująca logikę gry */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int t) throws SlickException {
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			LoadData.username = login.getText();
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	/** Metoda renderująca ekran gry */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		LoadData.background.draw(0, 0, Color.gray);
		LoadData.logo.draw(gc.getWidth()/2-LoadData.logo.getWidth()/2, LoadData.logo.getHeight()/5);
		uniFont.drawString(gc.getWidth()/2-uniFont.getWidth(text)/2, gc.getHeight()/2-uniFont.getHeight(text)*2, text, Color.white);
		login.setFocus(true);
		login.setBorderColor(Color.black);
		login.setTextColor(Color.green);
		login.setCursorVisible(false);
		login.render(gc, g);
	}

	/** Metoda pobierająca identyfikator stanu gry */
	@Override
	public int getID() {
		return 0;
	}
}
