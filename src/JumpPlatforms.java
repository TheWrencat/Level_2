import java.awt.Color;
import java.awt.Graphics;

public class JumpPlatforms extends JumpObjects {
	int ySpeed = 1;
	boolean jumpedOne = false;
	boolean jumpedTwo = false;
	JumpAnimation Platform = new JumpAnimation();

	JumpPlatforms(int x, int y, int width, int height) {
		super(x, y, width, height);
		Platform.loadImage("Platform.png");

	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
		Platform.update();
		y += ySpeed;

		if (y >= 750) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.drawImage(Platform.currentImage, x, y, width, height, null);
	}

}
