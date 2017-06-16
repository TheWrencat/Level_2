import javax.swing.JFrame;

public class JumpRunner {
	//Member Variables
	static final int FRAME_WIDTH = 500;
	static final int FRAME_HEIGHT = 800;
	JFrame frame;
	JumpPanel panel;
	
	//Constructor
	JumpRunner(){
		frame = new JFrame();
		panel = new JumpPanel();
	}
	
	//Setup
	void setup(){
		frame.setVisible(true);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Run (main method)
	public static void main(String[] args) {
		JumpRunner runner = new JumpRunner();
		runner.setup();
	}
	
}
