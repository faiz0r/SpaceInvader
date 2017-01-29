package spaceInvadeF;

import processing.core.*;

public class Asteroid extends PApplet {

	static PApplet parent;
	static PImage sequence1, sequence2, sequence3, sequence4, sequence5, sequence6;//the sequence process for the animation of the assteroid images
	int x;// cooridinates of the asteroids
	int y;
	int counter = 0;
	int velocityX;

	Asteroid(PApplet p, int x, int y, int dx) {//asteroid constructor
		parent = p;
		this.x = x;
		this.y = y;
		this.velocityX = dx;
		sequence1 = loadImage("Asteroid.png");//sequence1 will load the first asteroid image
		sequence2 = loadImage("Asteroid2.png");//sequence 2 will load the 2nd image rotated slightly and below they will do the same
		sequence3 = loadImage("Asteroid3.png");
		sequence4 = loadImage("Asteroid4.png");
		sequence5 = loadImage("Asteroid5.png");
		sequence6 = loadImage("Asteroid6.png");
		sequence1.resize(40, 40);//declaring each of the sequences
		sequence2.resize(40, 40);
		sequence3.resize(40, 40);
		sequence4.resize(40, 40);
		sequence5.resize(40, 40);
		sequence6.resize(40, 40);
	}
	
	boolean isShot(Bullet bullet)//this is the collision method taken from week 9 on moodle
	{
		if (bullet!=null)
		{
			if(abs(this.x-bullet.x)<20 && abs(this.y-bullet.y)<20)
				return true;
		}
		return false;
		}
	
	
	boolean finished() {//at what part the asteroid will make collision to end the game
		return y > (parent.height);//in this case it will be the bottom corner of the canvas
	}

	boolean reachedEdge() {//at which point the asteroids will collide with the side to then move to the other side
		return this.x > parent.width - 30;// screen width including the size of
											// the image
	}

	boolean reachedLeft() {//at what point the asteroids will collide with the left side to then move to the right
		return this.x <= 0;
	}

	void move() {//how to asteorids move across the screen
		if (reachedEdge()) {
			velocityX = velocityX * -1;
			y = y + 50;
		}
		if (reachedLeft()) {
			velocityX = velocityX * -1;
			y = y + 50;
		}

		x = x + velocityX;
	}

	public  void render()//below are the processes of the animation and the intervals between each image
	   {
	      if (counter<10)
	      {
	        parent.image(sequence1,x,y);
	      }
	      else if (counter<20)
	      {
	        parent.image(sequence2,x,y);
	      }
	      else if (counter<30)
	      {
	        parent.image(sequence3,x,y);
	      }
	      else if (counter<40)
	      {
	        parent.image(sequence4,x,y);
	      }
	      else if (counter<50)
	      {
	        parent.image(sequence5,x,y);
	      }
	      else if (counter<60)
	      {
	        parent.image(sequence6,x,y);
	      }
	      else 
	      {
	        counter=0; 
	      }
	      counter = counter+1;
	   }
}