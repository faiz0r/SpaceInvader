package spaceInvadeF;

import java.util.Random;

import processing.core.*;

public class mainInvaderClass extends PApplet {

	PImage background; //variable for the background
	PImage Logo;
	Random rand = new Random();
	int x = 0;//declaring the variable for x that will be used for various things such as the background and its movement 
	static int spX = 300;//declaring the movement for my player and the starting position of the player
	final int GAMEON = 0;//declaring the game on method and the processes involved whilst the game is on
	final int GAMEOVER = 1;//declaring game over and the processes involved whilst the game is off
	int gameMode = GAMEON;//anywhere where this code is seen will mean that whatever comes after will happen whilst the game is on
	int counter = 0;//declaring the counter variable used for bullets being shot so that the game can end when reached a certain integer
	int score = 0;//declaring the score variable used for incrementing the score after each asteroid is shot
	int TimeFirst;
	int TimeSec = 200;
	int AsteroidBullet = 0;
	Bullet bullet;//bullet method used to display the bullet and also used for the collision detection
	Asteroid[][] asters = new Asteroid[10][5];//2d array for my asteroids going 10 across and 5 down
	AsteroidBullet[] astersbullet = new AsteroidBullet[200];
	AsteroidBullet asBullet;
	boolean splash = true;
	boolean play = false;
	
	boolean health1 = true;
	boolean health2 = true;
	boolean health3 = true;
	
	public void setup() {
		Logo = loadImage("Logo.png");
		Logo.resize(700,800);
		size(600, 650);//size of the canvas
		//background(0);
		background = loadImage("spaceBackground.png");//loading and image for the background
		background.resize(width, height);//the background will stretch to the width and height of the canvas
		Player play = new Player(this);//taking the Player variable from the player class
		

		for (int k = 0; k < 5; k++) {//the loop in which the asteroids will be displayed in
			for (int i = 0; i < 10; i++)
				asters[i][k] = new Asteroid(this, i * 50 + 1, 40 * k, 3);//the layout of how the loop will be displayed and also the speed
		}
	}

	public void keyPressed() {//key pressed class which handles any physical logic
		if (key == CODED) {
			if (keyCode == LEFT)//when the left arrow is pressed
				control(-10);//move -10x pixels
			if (keyCode == RIGHT)//when the right arrow is pressed
				control(10);//move +10 pixels
		}
		if(keyCode == 'S'){
			splash = false;
			play = true;
		} 
		if (key == ' ' && gameMode == GAMEOVER) {//when the game is over tap space bar to activate the code below
			health1 = true;
			health2 = true;
			health3 = true;
			gameMode = GAMEON;//game will turn back on
			score = 0;//score will go back to 0
			setup();//restart the setup class used above
			for (int k = 0; k < 5; k++) {//use the for loop again
				for (int i = 0; i < 10; i++)
					asters[i][k] = new Asteroid(this, i * 50 + 1, 40 * k, 3);
			}
		}
		if (key == ' ') {//when the space-bar is pressed
			bullet = new Bullet(this, spX + 20, 600, -10);//bullet will move in this direction
		}
	}

	public void control(int direction) {//the action that is displayed when keys are pressed
		spX = spX + direction * 1;//how many pixels the player will move across
		if (spX > width - 45)//the area in which the player will make contact with the edge of the canvas and will move to the other side
			spX = 0;
		if (spX < 0)
			spX = width - 50;
	}
	
	public void deadScreen(){
		background(000);//back round will go black
		textSize(40);//text size for Game Over
		text("Game Over", 200, 325);//the text Game Over and its position
		textSize(10);//text size for Press (SPACEBAR) to continue
		text("Press (SPACEBAR) to continue", 230, 340);//the text Press (SPACEBAR) to continue and its position
		gameMode = GAMEOVER;//this is calling the key press method for when the game is over so that the game can restart
	}

	public void draw() {
		if(splash){
			image(Logo,-50,10);
			image(background, 0, x);
			image(background, 0, x + background.height);//image will loop when scrolling up
			x -= 2;//the speed of the background image scrolling up
			if (x == -background.height)
				x = 0; // wrap background
			
						
		}
		if(play){
		if (gameMode == GAMEON) {//the processes below will display when the game it is on
			image(background, 0, x);
			image(background, 0, x + background.height);//image will loop when scrolling up
			x -= 2;//the speed of the background image scrolling up
			
			if (x == -background.height)
				x = 0; // wrap background
			for (int i = 0; i < 10; i++) {//use the for loop again
				for (int k = 0; k < 5; k++) {
					if (asters[i][k] != null) {//2d array being used for if the asteroid is shot
						asters[i][k].render();//declaring the render method form the Asteroid class
						asters[i][k].move();//declaring the move method from the Asteroid class
						int drop = rand.nextInt(1000);
						if 
						(drop == 1 ){
							astersbullet[AsteroidBullet] = new AsteroidBullet (this,asters[i][k].x, asters[i][k].y, 2);
							AsteroidBullet++;
						}
						for(int as = 0; as < astersbullet.length; as++){//hearts when being shot
							if (Player.isShot(astersbullet[as])) {
								if(!health3 && !health2 && health1){
									health1 = false;
								}
								if(!health3 && health2){
									health2 = false;
								}
								if(health3){
									health3 = false;
								}
							}
						}

						if (asters[i][k].finished()) {//when all asteroids are shot the game will end and will display the code below
							deadScreen();
						}
						if (asters[i][k].isShot(bullet)) {//calling the isShot method from the asteroid class when the array of asteroids are being shot
							score++;//score will increment by 1 each time an asteroid disappears
							asters[i][k] = null;//the grid for the image within the array will = to null
							bullet = null;//and the bullet will also = to null once the collision is made
						}
					}
				}
			}
			for (int a=0;a<AsteroidBullet;a++)
			{
				astersbullet[a].move();
				astersbullet[a].render();
			}
			
				if (health1) {
					textSize(40);
					text("♥", 50, 50);
				}
				if (health2) {
					textSize(40);
					text("♥", 100, 50);
				}
				if (health3) {
					textSize(40);
					text("♥", 150, 50);
				}
			
				if(!health3 && !health2 && !health1){
					deadScreen();
				}
				
			textSize(40);//the text size for the Score
			text(score, 525, 50);//the method for text and where its position is
			Player.makePlayer();//putting the player on the screen
			if (bullet != null) {
				bullet.move();//declaring the move method from the bullet class
				bullet.render();//declaring the render method from the bullet class
				
			}

			counter = 0;//counter needs to reach 0 for the game to end
			for (int i = 0; i < 10; i++) {//for loop for the array of asteroids
				for (int k = 0; k < 5; k++) {
					if (asters[i][k] != null) {//if a a grid from my array is not equal to null
						counter++;//The counter will increment
					}
				}

			}
			}

			if (counter == 0) {//the counter will reset
				background(000);
				play = false;
				textSize(40);
				text("Well Done!", 200, 325);
				textSize(10);
				text("Press (SPACEBAR) to continue", 230, 340);
				textSize(40);
				text(score, 525, 40);
				gameMode = GAMEOVER;
			}

		}

	}
}
