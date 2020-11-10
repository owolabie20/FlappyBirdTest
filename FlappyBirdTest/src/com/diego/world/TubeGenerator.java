package com.diego.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.diego.main.Game;

public class TubeGenerator {
	public static List<Tube> tubes;
	private int ticks = 0, maxTicks = 60*1;
	private Random rand;
	private int width = 20, height;
	private Tube tube;
	private final int spaceBetween = 50; // Make sure that you have space between the tubes	
	
	public TubeGenerator() {
		tubes = new ArrayList<Tube>();
		rand = new Random();
		
		// Over tube
		height = rand.nextInt(60 - 15) + 15;
		tube = new Tube(Game.WIDTH , 0, width, height);
		tubes.add(tube);		
		
		// Under tube
		tube = new Tube(Game.WIDTH, height + spaceBetween, width, Game.HEIGHT);
		tubes.add(tube);
	}
	
	public void tick() {
		ticks++;
		
		if(ticks >= maxTicks) {	
			// Over tube
			height = rand.nextInt(60 - 15) + 15;
			tube = new Tube(Game.WIDTH , 0, width, height);
			tubes.add(tube);		
			
			// Under tube
			tube = new Tube(Game.WIDTH, height + spaceBetween, width, Game.HEIGHT);
			tubes.add(tube);
			
			ticks = 0;
		}
		
		for(Tube tube: tubes) {
			tube.tick(); 
			
			if(tube.isColliding(tube, Game.player)) {
				Game.restart();
				return;
			}
			
			if(tube.x < 0 - tube.w) {
				tubes.remove(tube);
				Game.score += 0.5;
				return;
			}
		}
		
	}
	
	public void render(Graphics g) {
		for(Tube tube: tubes) {
			tube.render(g);
		}
	}
	
}