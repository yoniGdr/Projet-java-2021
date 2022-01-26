package game.warGame;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import org.junit.Test;

import game.Character;
import game.Player;
import game.Tile;
import game.tile.*;
import game.util.exception.*;

public class ArmyTest {
    
	
    @Test
    public void testEat () throws WrongSizeCharacterException, NotEnoughFoodException {
    	WarPlayer yoni  = new  WarPlayer("le joueur", true);
    	Tile position = new Desert(1,2);
        Army army = new Army("les guerriers", position, 0, 5, yoni);
        int res = army.eat(5);
        assertTrue(res==10);
        Tile tile1 = new Mountain(1,3);
        Army army1 = new Army("les guerriers 2 ", tile1, 0, 5, yoni);
        assertEquals(army1.eat(5),5);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Mountain(1,3));
        tiles.add(new Mountain(1,4));
        tiles.add(new Mountain(1,5));
        tiles.add(new Desert(1,5));
        tiles.add(new Desert(4,5));
        for (Tile tile: tiles) {
        	if (tile instanceof Desert) {
        		int i =1;
        		while (i<6) {
        		Army army2 = new Army("les guerriers 2 ", tile, 0, i, yoni);
        		assertEquals(army2.eat(i),2*i);
        		i++;
        		}}
        	else if (tile instanceof Mountain) {
        		int i =1;
        		while (i<6) {
        		Army army3 = new Army("les guerriers 2 ", tile, 0, i, yoni);
        		assertEquals(army3.eat(i),i);
        		i++;
        		}}
        	
        }
    }
    
    @Test
    public void reduceSizeTest() throws WrongSizeCharacterException {
    	WarPlayer yoni  = new  WarPlayer("le joueur", true);
    	Tile position = new Desert(1,2);
    	Army army = new Army("les guerriers", position, 0, 5, yoni);
    	int i = 0;
    	while (i< 5) {
    		int res = army.getSize();
    		army.reduceSize(i);
    		assertTrue(army.getSize()==(res-i));
    		i++;
    	}
    	
    	
    }
    
    @Test 
    public void addGoldTest() throws WrongSizeCharacterException {
    	WarPlayer yoni  = new  WarPlayer("le joueur", true);
    	Tile position = new Desert(1,2);
    	Army army = new Army("les guerriers", position, 0, 5, yoni);
    	int i = 0;
    	while (i< 5) {
    		int res = army.getGold();
    		army.addGold(i);
    		assertTrue(army.getGold()==(res+i));
    		i++;
    	}
    }
    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.warGame.ArmyTest.class
        );
    }

    
    
}
    
    
    