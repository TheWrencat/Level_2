import java.awt.Color;
import java.awt.Graphics;

public class JumpPlayer extends JumpObjects {
	int speed;

	JumpPlayer(int x, int y, int width, int height) {
		speed = 5;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	void update() {

	}

	void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);

	}
}
