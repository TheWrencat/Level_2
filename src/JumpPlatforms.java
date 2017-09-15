import java.awt.Color;
import java.awt.Graphics;

public class JumpPlatforms extends JumpObjects {
	int ySpeed = 1;
	boolean jumpedOne = false;
	boolean jumpedTwo = false;

	JumpPlatforms(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {
		collisionBox.setBounds(x, y, width * 2, height / 10);

		y += ySpeed;

		if (y >= 750) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width * 2, height / 2);
	}

}
