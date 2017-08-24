import java.awt.Color;
import java.awt.Graphics;

public class JumpPlayer extends JumpObjects {
	JumpPlatforms platform;
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
		//Player speed
		x += xSpeed * 5;
		
		//inAir rules
		//On Platform
		if(platform != null) {
			y = platform.y - height+1;
			inAir=false;
		}else {
			y += ySpeed * 10;
			
		}
		
		//On ground
		if(y >= 700 ){
			y = 700;
			
			inAir=false;
		}	else{
		ySpeed += yGravity;
			//yGravity = .05;
			//inAir=true;	
		}
		
		

	}
	
	void setPlatform(JumpPlatforms p) {
		platform = p;
	}

	void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);

	}
}
