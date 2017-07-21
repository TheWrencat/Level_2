import java.awt.Color;
import java.awt.Graphics;

public class JumpPlatforms extends JumpObjects {
	int ySpeed = 1;

	JumpPlatforms(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {
		y += ySpeed;

	}

	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width * 2, height / 2);
	}

}
