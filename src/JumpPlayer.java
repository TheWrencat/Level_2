import java.awt.Color;
import java.awt.Graphics;

public class JumpPlayer extends JumpObjects {
	JumpPlatforms platform;
	int xSpeed;
	double ySpeed;
	double yGravity;
	boolean inAir;
	boolean startSafe;
	Color playerColor;

	JumpPlayer(int x, int y, int width, int height, Color playerColor) {
		super(x, y, width, height);
		this.playerColor = playerColor;
		xSpeed = 0;
		ySpeed = 0;
		yGravity = .05;
		inAir = false;
		startSafe = true;

		// False when on ground

	}

	void update() {
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
		g.drawImage(JumpPanel.TwoTest, x, y, width, height, null);

	}
}
