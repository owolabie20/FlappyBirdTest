package com.diego.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.diego.main.Game;

public class Player {
	private final int SPEED = 2;
	public  boolean isPressed;
	
	public int x, y, width, height;
	public static int maskx = 0, masky = 1, maskw = 16, maskh = 14;
	BufferedImage[] sprite;
	double speed;
	
	public Player(int x, int y, int width, int height,double speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		
		sprite = new BufferedImage[2];
		sprite[0] = Game.spritesheet.getSprite(0,0,16,16);
		sprite[1] = Game.spritesheet.getSprite(16,0,16,16);
	}
	
	public void tick(){
		if(isPressed) {
			y -= SPEED;
		}else {
			y += SPEED;
		}
		
		if(y < width)
			y = width;
		
		// System.out.println("Y: " + y + " GH: "+ Game.HEIGHT);
		
		if(y > Game.HEIGHT)
			Game.restart();
			
		/* To debug */
		//if(y > Game.HEIGHT - 40)
			//y = Game.HEIGHT - 40;
		
	}

	public void render(Graphics g) {	
		if(isPressed)
			g.drawImage(sprite[0], x, y, width, height, null);
		else
			g.drawImage(sprite[1], x, y, width, height, null);
		
		//g.setColor(Color.red);
		//g.fillRect(x + maskx, y + masky, maskw, maskh);
	}

}
