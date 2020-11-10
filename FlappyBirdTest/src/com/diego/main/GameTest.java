package com.diego.main;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diego.entities.Player;
import com.diego.world.Tube;
class GameTest {
    Game game;
    Tube tube1;
    Tube tube2;

    @BeforeEach
    void setUp() throws Exception {
	game = new Game(); // start game with initial parameter
	tube1 =  game.tubes.tubes.get(0); //get first tube created
	tube2 = game.tubes.tubes.get(1);
    }
    
    //Test Scenario 1
    @Test // Case 1- verify player original inputs
    void testPlayer() {
	Player player = new Player(game.WIDTH/2 - 60,game.HEIGHT/2,16,16,2); //original setting
	//check that tube is not colliding
	assertEquals(player.x, game.player.x); assertEquals(player.y, game.player.y);
	assertEquals(false, tube1.isColliding(tube1, player)); //check if they are colliding
	assertEquals(false, tube2.isColliding(tube2, player)); //check if they are colliding
    }
    @Test // Case 2- verify play restart inputs
    void testPlayer2() {
	Player player = new Player(game.WIDTH/2 - 30,game.HEIGHT/2,16,16,2); //restart setting
	//check that tube is not colliding
	assertNotSame(player.x, game.player.x); assertEquals(player.y, game.player.y);
	assertEquals(false, tube1.isColliding(tube2, player)); //check if they are colliding
	assertEquals(false, tube2.isColliding(tube2, player)); //check if they are colliding
    }
    @Test // Case 3- set player to be positioned at the same horizontal position check over tube
    void testPlayer3() {
	Player player = new Player(game.WIDTH/2, game.HEIGHT/2,16,16,2); //changed x position
	tube1.x = game.WIDTH/2;
	assertEquals(false, tube1.isColliding(tube1, player)); //check if they are colliding
    }
    @Test // Case 4- set player to be positioned at the same horizontal position check under tube
    void testPlayer4() {
	Player player = new Player(game.WIDTH/2, game.HEIGHT/2,16,16,2); //changed x position
	tube2.x = game.WIDTH/2;
	assertEquals(false, tube2.isColliding(tube2, player)); //check if they are colliding
    }
    @Test // Case 5- should fail when player is placed at same horizontal and vertical distance as tube
    void testPlayer5() {
	Player player = new Player(game.WIDTH/2, 0,16,16,2); //changed y position
	tube1.x = game.WIDTH/2;
	tube1.y = 0;
	assertEquals(true, tube1.isColliding(tube1, player)); //check if they are colliding
    }
    
    
    //Test Scenario 2- Review Sufficient Space for Player to Move Through
    @Test //Case 1- check original setting does not collide
    void testFreeSpace() {
	Player player = new Player(game.WIDTH/2 - 60, (tube2.y+tube1.h)/2,16,16,2);
	tube1.x = game.WIDTH/2 - 60; tube2.y = game.WIDTH/2 - 60;
	assertEquals(false, tube1.isColliding(tube1, player));
	assertEquals(false, tube2.isColliding(tube2, player));
    }
    @Test //Case 2- check original setting does not collide with player near the over tube
    void testFreeSpace2() {
	Player player = new Player(game.WIDTH/2 - 60, ((tube2.y+tube1.h)/2) - 25,16,16,2);
	tube1.x = game.WIDTH/2 - 60; tube2.y = game.WIDTH/2 - 60;
	assertEquals(false, tube1.isColliding(tube1, player));
	assertEquals(false, tube2.isColliding(tube2, player));
    }
    @Test //Case 3- check original setting does not collide with player near the under tube
    void testFreeSpace3() {
	Player player = new Player(game.WIDTH/2 - 60, ((tube2.y+tube1.h)/2) + 25,16,16,2);
	tube1.x = game.WIDTH/2 - 60; tube2.y = game.WIDTH/2 - 60;
	assertEquals(false, tube1.isColliding(tube1, player));
	assertEquals(false, tube2.isColliding(tube2, player));
    }
    @Test //Case 4- check original setting does not collide with player when exactly player height away from
    // the over tube
    void testFreeSpace4() {
	Player player = new Player(game.WIDTH/2 - 60, (tube2.y+tube1.h) - 34,16,16,2);
	tube1.x = game.WIDTH/2 - 60; tube2.y = game.WIDTH/2 - 60;
	assertEquals(false, tube2.isColliding(tube2, player));
	assertEquals(false, tube1.isColliding(tube1, player));
    }
    @Test //Case 5- check original setting does collide with player really close to the over tube
    void testFreeSpace5() {
	Player player = new Player(game.WIDTH/2 - 60, (tube2.y+tube1.h) - 50,16,16,2);
	tube1.x = game.WIDTH/2 - 60; tube2.y = game.WIDTH/2 - 60;
	assertEquals(false, tube2.isColliding(tube2, player));
	assertEquals(true, tube1.isColliding(tube1, player));
    }
    @Test //Case 6- check original setting does not collide with player when exactly player height away from
    // the under tube
    void testFreeSpace6() {
	Player player = new Player(game.WIDTH/2 - 60, (tube2.y+tube1.h) + 34,16,16,2);
	tube1.x = game.WIDTH/2 - 60; tube2.y = game.WIDTH/2 - 60;
	assertEquals(false, tube1.isColliding(tube1, player));
	assertEquals(false, tube2.isColliding(tube2, player));
    }
    @Test //Case 7- check original setting does not collide with player really near the under tube
    void testFreeSpace7() {
	Player player = new Player(game.WIDTH/2 - 60, (tube2.y+tube1.h) + 50,16,16,2);
	tube1.x = game.WIDTH/2 - 60; tube2.y = game.WIDTH/2 - 60;
	assertEquals(false, tube1.isColliding(tube1, player));
	assertEquals(true, tube2.isColliding(tube2, player));
    }
    
    
    //Test Scenario 3- Review if correctly calculates collision
    @Test //Case 1- check no collision when the player first enters the game
    void testCollision() {
	assertEquals(false, tube1.isColliding(tube1, game.player));
	assertEquals(false, tube2.isColliding(tube2, game.player));
    }
    @Test //Case 2- check collision when the player is at over tube position
    void testCollision2() {
	Player player = new Player(tube1.x,tube1.y,16,16,2); 
	assertEquals(true, tube1.isColliding(tube1, player));
    }
    @Test //Case 3- check collision when the player is at under tube position
    void testCollision3() {
	Player player = new Player(tube2.x,tube2.y,16,16,2); 
	assertEquals(true, tube2.isColliding(tube2, player));
    }
    @Test //Case 4- check collision when the player is at height distance away from over tube position
    void testCollision4() {
	Player player = new Player(tube1.x,tube1.y+16,16,16,2);
	assertEquals(true, tube1.isColliding(tube1, player));
    }
    @Test //Case 5- check collision when the player is at height distance away from under tube position
    void testCollision5() {
	Player player = new Player(tube2.x,tube2.y-16,16,16,2); 
	assertEquals(true, tube2.isColliding(tube2, player));
    }
    @Test //Case 6- check collision when the player is at less than height distance away from over tube position
    void testCollision6() {
	Player player = new Player(tube1.x,tube1.y+15,16,16,2);
	assertEquals(true, tube1.isColliding(tube1, player));
    }
    @Test //Case 7- check collision when the player is at less than height distance away from under tube position
    void testCollision7() {
	Player player = new Player(tube2.x,tube2.y-15,16,16,2);
	assertEquals(true, tube2.isColliding(tube2, player));
    }
    @Test //Case 8- check collision when the player is at more than height distance away from over tube position
    void testCollision8() {
	Player player = new Player(tube1.x,tube1.y+17,16,16,2);
	assertEquals(false, tube1.isColliding(tube1, player));
    }
    @Test //Case 9- check collision when the player is at more than height distance away from under tube position
    void testCollision9() {
	Player player = new Player(tube2.x,tube2.y-17,16,16,2);
	assertEquals(false, tube2.isColliding(tube2, player));
    }
    
    
    //Test Scenario 4- Score Update
    //@Test
    void testScore() {
	
	
    }
    
    //Test Scenario 5- GUI graphics
    //@Test
    void testGUI() {
	
    }

}
