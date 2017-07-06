import java.awt.Graphics;

public class JumpObjects {
	// Member variables
	JumpPanel panel;
	int x;
	int y;
	int width;
	int height;

	boolean up;
	boolean down;
	boolean left;
	boolean right;

	JumpObjects() {
		panel = new JumpPanel();
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
		g.fillRect(x, y, 100, 100);
	}

}
