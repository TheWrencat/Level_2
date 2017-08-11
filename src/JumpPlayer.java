import java.awt.Color;
import java.awt.Graphics;

public class JumpPlayer extends JumpObjects {
	int xSpeed;
	double ySpeed;
	double yGravity;
	boolean inAir;

	JumpPlayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		xSpeed = 0;
		ySpeed = 0;
		yGravity = .05;
		inAir = false;

	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
		x += xSpeed * 5;
		y += ySpeed * 10;
		ySpeed += yGravity;
		if(y >= 700){
			y = 700;
			yGravity = 0;
			inAir=false;
		}else{
			yGravity = .05;
			inAir=true;
			
		}

	}

	void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);

	}
}
