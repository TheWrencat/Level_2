import java.awt.Color;
import java.awt.Graphics;

public class JumpPlayer extends JumpObjects {
	int xSpeed;
	int ySpeed;

	JumpPlayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		xSpeed = 0;
		ySpeed = 0;

	}

	void update() {
		x += xSpeed * 4;
		y += ySpeed * 4;

	}

	void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);

	}
}
