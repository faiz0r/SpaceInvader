package spaceInvadeF;

import java.util.ArrayList;

import processing.core.PApplet;

public class AsteroidBullet {

	static PApplet parent;
	int x;
	int y;
	int velocityY = 1;
	boolean hitSpace = false;
	boolean visable = false;
	boolean dead = true;
	int bulletLimit = 50;
	int bulletRange = 200;

	AsteroidBullet(PApplet p, int x, int y, int dy)// constructor
	{
		parent = p;
		this.x = x;
		this.y = y;
		this.velocityY = dy;
	}

	void move() {

		y = y + velocityY;
	}

	void render() {
		parent.rect(x, y, 5, 15);
	}

}
