import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JumpPanel extends JPanel implements ActionListener, KeyListener {
	// Member Variables
	JumpObjects objects;
	JumpPlayer player;
	JumpPlayer playerTwo;
	JumpObjectsManager manager;
	String imagePickerOne;
	String imagePickerTwo;
	Font titleFont;
	Font subFont;
	int saveScoreOne;
	int saveScoreTwo;
	// menu states
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	int whoDied = 0;
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
		subFont = new Font("Arial", Font.PLAIN, 24);

		manager.addPlayers(player);
		manager.addPlayers(playerTwo);
		

	}

	// Start Game
	void startGame() {
		timer.start();

	}

	// Draw States
	void drawMenuState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		g.setColor(Color.GRAY);
		g.setFont(titleFont);
		g.drawString("Jump", 180, 200);
		g.drawString("START GAME", 85, 250);
		g.setColor(Color.PINK);
		
		//g.drawImage(TwoTest, 300, 700, 50, 50, null);
		g.setColor(Color.ORANGE);
		//g.drawImage(walk.currentImage, 200, 700, 50, 50, null);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		g.setFont(subFont);
		g.setColor(Color.PINK);
		g.drawString("Player One:"+ manager.scoreOne + " ", 1, 24);
		g.setColor(Color.ORANGE);
		g.drawString("Player Two:"+ manager.scoreTwo + " ", 1, 48);
		manager.draw(g);
		
		
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 200);
		g.setFont(subFont);
		if(whoDied == 1) {
			g.drawString("SORRY, PLAYER ONE.", 120, 250);
		}else if(whoDied == 2) {
			g.drawString("SORRY, PLAYER TWO.", 120, 250);
		}
		g.setColor(Color.WHITE);
		g.fillRect( 120, 280, 250, 80);
		g.setColor(Color.PINK);
		g.drawString("PLAYER ONE: " + saveScoreOne, 130, 310);
		g.setColor(Color.ORANGE);
		g.drawString("PLAYER TWO: " + saveScoreTwo, 130, 340);
	}

	// Update States
	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		manager.checkCollision();
		manager.manageEnemies();
		
	}

	void updateEndState() {
		//reset
		
		if(!player.isAlive) {
			whoDied = 1;
		}else if(!playerTwo.isAlive) {
			whoDied = 2;
		}
		if(!player.isAlive || !playerTwo.isAlive) {
			saveScoreOne = manager.scoreOne;
			saveScoreTwo = manager.scoreTwo;
		}
		
		
		
		player.isAlive = false;
		playerTwo.isAlive = false;
		manager.update();
		player = new JumpPlayer(300, 700, 50, 50, Color.PINK);
		playerTwo = new JumpPlayer(200, 700, 50, 50, Color.ORANGE);
		manager.reset();
		manager.addPlayers(player);
		manager.addPlayers(playerTwo);
		
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
			currentState = 2;
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
			player.animationSetOne = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.xSpeed = +2;
			player.animationSetOne = 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.xSpeed = -2;
			player.animationSetOne = 3;
			//imagePickerOne = "JumpSpriteLeft.png"
		}

		// Player Two Controls
		if (e.getKeyCode() == KeyEvent.VK_W && !playerTwo.inAir) {
			playerTwo.y -= 2;
			playerTwo.ySpeed = -2;
			playerTwo.inAir = true;
			playerTwo.animationSetOne = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			playerTwo.xSpeed = +2;
			playerTwo.animationSetOne = 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			playerTwo.xSpeed = -2;
			playerTwo.animationSetOne = 3;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER && currentState != GAME_STATE) {
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
			player.animationSetOne = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.xSpeed = 0;
			player.animationSetOne = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.xSpeed = 0;
			player.animationSetOne = 0;
		}

		// Player two stopping
		if (e.getKeyCode() == KeyEvent.VK_W) {
			playerTwo.ySpeed = 0;
			playerTwo.animationSetOne = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			playerTwo.xSpeed = 0;
			playerTwo.animationSetOne = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			playerTwo.xSpeed = 0;
			playerTwo.animationSetOne = 0;
		}
	}

}
