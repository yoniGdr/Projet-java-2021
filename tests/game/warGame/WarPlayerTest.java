package game.warGame;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;

import game.*;
import game.board.*;
import game.resource.*;
import game.tile.*;
import game.util.exception.*;
import junit.framework.TestCase;
import org.junit.Test;

public class WarPlayerTest{
	
	
	private  WarPlayer yoni;
    private Army army;
    private Tile desert;
    
	@Before
	public void before() throws WrongSizeCharacterException {
		
		 
        this.yoni = new WarPlayer("Yoni",true);
        this.desert = new Desert(0, 0);	
        this.army = new Army("Boss",this.desert,0,5,yoni);
		
	    
	}
    @Test
    public void testDestroyCharacter() throws WrongSizeCharacterException {
    	Army army2 = new Army("les guerriers",this.desert,1,1,yoni);
        assertEquals(this.yoni.getGold(),0);
        assertFalse(yoni.getDeployedCharacters().contains(this.army));
		this.yoni.adddeployedCharacter(this.army);
		assertTrue(yoni.getDeployedCharacters().contains(this.army));
        this.yoni.destroyCharacter(this.army);
        assertFalse(this.yoni.getDeployedCharacters().contains(this.army));
        this.yoni.adddeployedCharacter(army2);
        assertTrue(this.yoni.getDeployedCharacters().contains(army2));
        //this.yoni.destroyCharacter(army2);
    }
     
    @Test
    public void testSetWarriors() {
        assertEquals(yoni.getWarriors(),35);
        yoni.setWarriors(5);
        assertEquals(yoni.getWarriors(),30);
    }
    @Test
    public void getFoodTest() throws NotEnoughFoodException {
    	assertTrue(this.yoni.getFood()==10);
    	this.yoni.adddeployedCharacter(this.army);
    	assertTrue(this.yoni.getDeployedCharacters().contains(this.army));
    	this.yoni.reduceFood(0);
    	assertTrue(this.yoni.getFood()==10);
    	this.yoni.feedAllCharacter();
    	assertFalse(this.yoni.getFood()==10);
    	assertTrue(this.yoni.getFood()==0);
    	this.yoni.addFood(25);
    	assertFalse(this.yoni.getFood()==5);
    	
    }
    
    @Test
    public void testCountPoint() {
    	yoni.addGold(10);
    	yoni.addGold(10);
    	yoni.addPoints(2);
    	yoni.setTotalPoints(); 
       assertTrue(yoni.getPoints() == 22);
    }

    
    @Test
    public void interactiveTest() {
    	assertTrue(yoni.isInterfactive() == true);
    }
    @Test
    public void testHarvest() {
       assertTrue(yoni.getResource().size() == 0);
       int i = 0;
       while (i < 10) {
    	   yoni.addRessource(new Rock(i));
    	   yoni.addRessource(new Sand(i));
    	   i++;
       }
       assertTrue(yoni.getResource().size() == 20);
       yoni.removeAllResource();
       assertFalse(yoni.getResource().size() == 1);
       assertTrue(yoni.getResource().size() == 0);
    }
    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(game.warGame.WarPlayerTest.class);
    }}
