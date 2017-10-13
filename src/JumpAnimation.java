import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class JumpAnimation {

	ArrayList<BufferedImage> imageList;
	int frameDelay;
	BufferedImage currentImage;
	long startTime;
	int count = 0;

	public JumpAnimation() {
		imageList = new ArrayList<BufferedImage>();

	}

	public void loadImage(String file) {
		BufferedImage img;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream(file));
			imageList.add(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setFrameDelay(int f) {
		frameDelay = f;
	}

	public void update() {
		if (System.currentTimeMillis() - startTime >= frameDelay) {
			count++;
			if (count >= imageList.size()) {
				count = 0;
			}
			currentImage = imageList.get(count);
			startTime = System.currentTimeMillis();
		}

	}
}
