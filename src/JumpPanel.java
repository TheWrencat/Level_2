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
	JumpPlayer playerTwo;
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
		// objects = new JumpObjects();
		player = new JumpPlayer(300, 700, 50, 50, Color.PINK);
		playerTwo = new JumpPlayer(200, 700, 50, 50, Color.ORANGE);

		manager = new JumpObjectsManager();

		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);

		manager.addPlayers(player);
		manager.addPlayers(playerTwo);

	}

	// Start Game
	void startGame() {
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
		manager.draw(g);
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
		manager.update();
		manager.checkCollision();
		manager.manageEnemies();
		//reset
		//WRITE IT
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
		if (!player.isAlive || !playerTwo.isAlive) {
			currentState = END_STATE;
		}
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

		// Player One Controls
		if (e.getKeyCode() == KeyEvent.VK_UP && !player.inAir) {
			player.y -= 2;
			player.ySpeed = -2;
			player.inAir = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.xSpeed = +2;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.xSpeed = -2;
		}

		// Player Two Controls
		if (e.getKeyCode() == KeyEvent.VK_W && !playerTwo.inAir) {
			playerTwo.y -= 2;
			playerTwo.ySpeed = -2;
			playerTwo.inAir = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			playerTwo.xSpeed = +2;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			playerTwo.xSpeed = -2;
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
		// Player one stopping
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.ySpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.xSpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.xSpeed = 0;
		}

		// Player two stopping
		if (e.getKeyCode() == KeyEvent.VK_W) {
			playerTwo.ySpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			playerTwo.xSpeed = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			playerTwo.xSpeed = 0;
		}
	}

}
