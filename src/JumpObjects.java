import java.awt.Graphics;
import java.awt.Rectangle;

public class JumpObjects {
	// Member variables
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	Rectangle collisionBox;

	JumpObjects(int x, int y, int width, int height){
		isAlive = true;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(x, y, width, height);

	}

	void update() {
		 
		

	}

	void draw(Graphics g) {

	}

}
