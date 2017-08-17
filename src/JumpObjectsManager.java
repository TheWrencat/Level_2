
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class JumpObjectsManager {
	ArrayList<JumpObjects> objects;
	ArrayList<JumpPlayer> players;

	int score = 0;

	long enemyTimer = 0;
	int enemySpawnTime = 1000;

	public JumpObjectsManager() {
		objects = new ArrayList<JumpObjects>();
		players = new ArrayList<JumpPlayer>();
	}

	public void addObject(JumpObjects o) {
		objects.add(o);
	}
	public void addPlayers(JumpPlayer p) {
		players.add(p);
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			JumpObjects o = objects.get(i);
			o.update();
		}
		for (int j = 0; j < players.size(); j++) {
			JumpPlayer p = players.get(j);
			p.update();
		}


		purgeObjects();
		
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			JumpObjects o = objects.get(i);
			o.draw(g);
		}
		for (int j = 0; j < players.size(); j++) {
			JumpPlayer p = players.get(j);
			p.draw(g);
		}
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}

		}
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addObject(new JumpPlatforms(new Random().nextInt(JumpRunner.FRAME_WIDTH), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		for (int i = 0; i < players.size(); i++) {
			JumpPlayer player = players.get(i);
			
		for (int j = 0; j < objects.size(); j++) {
			JumpPlatforms platform = (JumpPlatforms) objects.get(i);
			if( player.y < platform.y) {
				if(player.y == platform.y + 1) {
					player.yGravity = 0;
					player.inAir = false;
					
				}
			}
				
			}
		}
		}
	

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	public void reset() {
		objects.clear();
	}
}
