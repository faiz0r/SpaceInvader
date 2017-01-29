package spaceInvadeF;

import processing.core.*;

public class Player extends PApplet {

	static PApplet parent;
	static PImage Player1;
	static int py = 600;

	Player(PApplet p) {
		parent = p;
		Player1 = loadImage("player.png");//loading the image of the player
	}

	public static void makePlayer() {

		parent.image(Player1, mainInvaderClass.spX, py);//position of the player
	}
	
	
	static boolean isShot(AsteroidBullet astersbullet)//this is the collision method taken from week 9 on moodle
	{
		if (astersbullet!=null)
		{
				if(abs(mainInvaderClass.spX-astersbullet.x)<30 && abs(py-astersbullet.y)<30){
					astersbullet.y = 10000;
				return true;
			}
		}
		return false;
	}


}