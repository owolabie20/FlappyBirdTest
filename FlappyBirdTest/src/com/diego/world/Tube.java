package com.diego.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.diego.entities.Player;

public class Tube {
	public int x, y, w, h;
	private final int SPEED = 1;
	
	public Tube(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void tick() {
		x -= SPEED;	
	}
	
	public boolean isColliding(Tube tube, Player player){
		Rectangle e1Mask = new Rectangle(tube.x, tube.y, tube.w, tube.h);
		Rectangle e2Mask = new Rectangle(player.x + Player.maskx, player.y + Player.masky, Player.maskw, Player.maskh);		
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, w, h);
	}
}