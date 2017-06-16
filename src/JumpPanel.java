import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class JumpPanel extends JPanel implements KeyListener {
	//Member Variables
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	
	//Constructor
	JumpPanel(){
		
	}

	//Draw States
	void drawMenuState(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("Jump", 70, 200);
		g.drawString("START GAME", 85, 250);
	}
	void drawGameState(Graphics g){
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		
	}
	void drawEndState(Graphics g){
		g.setColor(Color.RED);
		g.drawRect(0, 0, JumpRunner.FRAME_WIDTH, JumpRunner.FRAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER", 70, 200);
		g.drawString("RESTART", 85, 250);
	}
	
	//Update States
	void updateMenuState(){
		
	}
	
	void updateGameState(){
	
	}
	
	void updateEndState(){
		
	}
	
	//Paint
	public void paintComponent(Graphics g){
		g.fillRect(10, 10, 100, 100);
		
		if(currentState == MENU_STATE){
			drawMenuState(g);
			
			
		}else if (currentState == GAME_STATE){
			drawGameState(g);
		}else if(currentState == END_STATE){
			drawEndState(g);
		}
	}
	
	
	
	//Key Controls
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("key typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key pressed");
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			currentState++;
			if(currentState > END_STATE){
				currentState = MENU_STATE;
			}
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key released");
	}

}
