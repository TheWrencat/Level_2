
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class JumpObjectsManager {
	ArrayList<JumpObjects> objects;
	ArrayList<JumpPlayer> players;

	int scoreOne = 0;
	int scoreTwo = 0;

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

		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isAlive) {
				players.remove(i);
			}

		}
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime * 2) {
			addObject(new JumpPlatforms(new Random().nextInt(JumpRunner.FRAME_WIDTH), 0, 100, 25));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		if (players.size() < 2) {
			return;
		}
		JumpPlayer player = players.get(0);
		JumpPlayer playerTwo = players.get(1);

		if (player.collisionBox.intersects(playerTwo.collisionBox)) {

			if (player.x > playerTwo.x) {
				player.x += 5;
				playerTwo.x += -5;
			} else {
				player.x += -5;
				playerTwo.x += 5;
			}

		}

		boolean one = false;
		boolean two = false;
		for (int j = 0; j < objects.size(); j++) {

			JumpPlatforms platform = (JumpPlatforms) objects.get(j);
			// Player One
			if (player.ySpeed >= 0) {

				if (player.collisionBox.intersects(platform.collisionBox)) {
					one = true;
					player.setPlatform(platform);
					player.startSafe = false;
					if (!platform.jumpedOne) {
						platform.jumpedOne = true;
						scoreOne++;
						System.out.println(scoreOne);
					}
					

				}
			}

			// Player Two
			if (playerTwo.ySpeed >= 0) {

				if (playerTwo.collisionBox.intersects(platform.collisionBox)) {
					two = true;
					playerTwo.setPlatform(platform);
					playerTwo.startSafe = false;
					if(!platform.jumpedTwo) {
					platform.jumpedTwo = true;
					scoreTwo++;
					System.out.println(scoreTwo);
					}

				}

			}

		}
		// Player One
		if (!one) {
			players.get(0).setPlatform(null);
			if (players.get(0).y < 700) {
				players.get(0).inAir = true;
			}
		}

		// Player Two
		if (!two) {
			players.get(1).setPlatform(null);
			if (players.get(1).y < 700) {
				players.get(1).inAir = true;
			}
		}
	}

	public int getScoreOne() {
		return scoreOne;

	}

	public int getScoreTwo() {
		return scoreTwo;

	}

	public void setScoreOne(int s) {
		scoreOne = s;
	}

	public void setScoreTwo(int s) {
		scoreTwo = s;
	}

	public void reset() {
		objects.clear();
		players.clear();
		scoreOne = 0;
		scoreTwo = 0;
	}
}
