import java.awt.Color;
import java.awt.Graphics;

public class JumpObjects {
	// Member variables
	int x;
	int y;
	int width;
	int height;

	boolean isAlive;

	boolean up;
	boolean down;
	boolean left;
	boolean right;

	JumpObjects() {
		isAlive = true;

	}

	void update() {
		// collisionBox.setBounds(x, y, width, height);
		if (up == true) {
			y -= 5;
		}
		if (down == true) {
			y += 5;
		}
		if (right == true) {
			x += 5;
		}
		if (left == true) {
			x -= 5;
		}

	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 100, 100);
	}

}
