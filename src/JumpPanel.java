import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class JumpPanel extends JPanel implements ActionListener, KeyListener {
	// Member Variables
	JumpObjects objects;
	JumpPlayer player;
	JumpObjectsManager manager;
	Font titleFont;
	// menu states
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	// timer
	Timer timer;

	// Constructor
	JumpPanel() {
		objects = new JumpObjects();
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);

	}

	// Start Game
	void startGame() {
		player = new JumpPlayer(250, 700, 50, 50);
		timer.start();

	}

	// Draw States
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("Jump", 180, 200);
		g.drawString("START GAME", 85, 250);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		objects.draw(g);
		player.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 200);
		g.drawString("RESTART", 130, 250);
	}

	// Update States
	void updateMenuState() {

	}

	void updateGameState() {
		objects.update();
		player.update();
	}

	void updateEndState() {

	}

	// Paint
	public void paintComponent(Graphics g) {

		if (currentState == MENU_STATE) {
			drawMenuState(g);

		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	// Switch States
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}

	}

	// Key Controls
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("key typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			objects.up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			objects.down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			objects.right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			objects.left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState++;
			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			objects.up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			objects.down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			objects.right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			objects.left = false;
		}

	}

}
