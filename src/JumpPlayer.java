import java.awt.Color;
import java.awt.Graphics;

public class JumpPlayer extends JumpObjects {
	JumpAnimation walkLeft = new JumpAnimation();
	JumpAnimation walkRight = new JumpAnimation();
	JumpAnimation stand = new JumpAnimation();
	
	JumpPlatforms platform;
	int xSpeed;
	double ySpeed;
	double yGravity;
	boolean inAir;
	boolean startSafe;
	Color playerColor;
	
	//Animation Set
		int animationSetOne = 0;
	
	

	JumpPlayer(int x, int y, int width, int height, Color playerColor) {
		super(x, y, width, height);
		this.playerColor = playerColor;
		xSpeed = 0;
		ySpeed = 0;
		yGravity = .05;
		inAir = false;
		startSafe = true;
		if(playerColor == Color.PINK) {
		stand.loadImage("JumpSpriteStand.png");
		
		walkRight.loadImage("JumpSpriteRight.png");
		
		walkLeft.loadImage("JumpSpriteLeft.png");
		}
		if(playerColor == Color.ORANGE) {
			stand.loadImage("JumpSpriteStandTwo.png");
			
			walkRight.loadImage("JumpSpriteRightTwo.png");
			
			walkLeft.loadImage("JumpSpriteLeftTwo.png");
			}
		

		// False when on ground

	}

	void update() {
		stand.update();
		walkRight.update();
		walkLeft.update();
		
		
		
		collisionBox.setBounds(x, y, width, height);
		// Player speed
		x += xSpeed * 5;

		// inAir rules
		// The base of the interactions with the platform and the player so it's
		// really important or something
		if (platform != null) {
			y = platform.y - height + 1;
			inAir = false;
		} else {
			// Player speed control
			y += ySpeed * 5;

		}

		// hitting wall
		if (x <= 1) {
			x = 1;
			xSpeed = -xSpeed;
		}
		if (x >= 500) {
			x = 500;
			xSpeed = -xSpeed;
		}
		// On ground
		if (y >= 700 && startSafe) {
			y = 700;

			inAir = false;
		} else {
			ySpeed += yGravity;
		}

		if (y >= 750) {
			isAlive = false;
		}

	}

	void setPlatform(JumpPlatforms p) {
		platform = p;
	}

	void draw(Graphics g) {
		g.setColor(playerColor);
		//How to draw?
		
		if(animationSetOne == 0) {
			g.drawImage(stand.currentImage, x, y, width, height, null);
		}else if(animationSetOne == 1) {
			g.drawImage(stand.currentImage, x, y, width, height, null);
		}else if(animationSetOne == 2) {
			g.drawImage(walkRight.currentImage, x, y, width, height, null);
		}else if(animationSetOne == 3) {
			g.drawImage(walkLeft.currentImage, x, y, width, height, null);
		}
		
	}
}
