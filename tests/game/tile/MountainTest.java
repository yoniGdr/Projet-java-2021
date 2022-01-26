package game.tile;

import static org.junit.Assert.*;

import org.junit.Before;

import game.Character;
import game.Resource;
import game.Tile;
import game.board.ClassicBoard;
import game.farmGame.FarmPlayer;
import game.farmGame.Farmer;
import game.resource.Rock;
import game.resource.Sand;
import game.resource.Wood;
import game.tile.*;
import game.util.exception.*;
import game.warGame.Army;
import game.warGame.WarPlayer;
import junit.framework.TestCase;
import org.junit.Test;

public class MountainTest{
	private  WarPlayer yoni;
    private Army army;
    private Tile mountain;
    private FarmPlayer isi;
    private Farmer farm;
    
    @Before
	public void before() throws WrongSizeCharacterException {
		
		 this.isi = new FarmPlayer("isi", true);
        this.yoni = new WarPlayer("Yoni",true);
		this.mountain = new Mountain(0, 0);
		this.army = new Army("Boss",this.mountain,0,5,this.yoni);
	    this.farm = new Farmer(new Desert(0,0),  this.isi);
	
    }
	@Test 
    public void getPositionTest() {
    	assertTrue(this.mountain.getXposition() == 0);
    	assertTrue(this.mountain.getYposition() == 0);
    }
    @Test
    public void isBusyTest() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	WarPlayer zack = new WarPlayer("zack", true);
    	assertTrue(!this.mountain.isBusy());
    	try {
    		this.mountain.welcomeCharacter(this.army);
    	}
    	catch(TooManyCharacterforMountain e) {
    		this.army.reduceSize(2);
    		this.mountain.welcomeCharacter(this.army);
    	}
    		
    	assertTrue(this.mountain.isBusy());
    	assertNotNull(this.mountain.getPlayer());
    	assertNotNull(this.mountain.getCharacter());
    	this.mountain.setPlayer(zack);
    	assertFalse(this.mountain.getPlayer()==this.yoni);
    	this.mountain.removeCharacter();
    	assertNull(this.mountain.getCharacter());
    	assertNull(this.mountain.getPlayer());
    	assertFalse(this.mountain.isBusy());
    
    }
    @Test
    public void isBusyTest3() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	WarPlayer zack = new WarPlayer("zack", true);
    	assertTrue(!this.mountain.isBusy());
    	try {
    		this.mountain.welcomeCharacter(this.army);
    	}
    	catch(TooManyCharacterforMountain e) {
    		this.army.reduceSize(5);
    	}
    	try {
    		this.mountain.welcomeCharacter(this.army);
    	}
    	catch(WrongSizeCharacterException e) {
    		this.army.addSize(3);
    		this.mountain.welcomeCharacter(this.army);
    	}
    		
    	assertTrue(this.mountain.isBusy());
    	assertNotNull(this.mountain.getPlayer());
    	assertNotNull(this.mountain.getCharacter());
    	this.mountain.setPlayer(zack);
    	assertFalse(this.mountain.getPlayer()==this.yoni);
    	this.mountain.removeCharacter();
    	assertNull(this.mountain.getCharacter());
    	assertNull(this.mountain.getPlayer());
    	assertFalse(this.mountain.isBusy());
    
    }
    
    @Test
    public void isBusyTest2() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	FarmPlayer zack = new FarmPlayer("zack", true);
    	assertTrue(!this.mountain.isBusy());
    	
    	this.mountain.welcomeCharacter(this.farm);
    		
    	assertTrue(this.mountain.isBusy());
    	assertNotNull(this.mountain.getPlayer());
    	assertNotNull(this.mountain.getCharacter());
    	this.mountain.setPlayer(zack);
    	assertFalse(this.mountain.getPlayer()==this.yoni);
    	this.mountain.removeCharacter();
    	assertNull(this.mountain.getCharacter());
    	assertNull(this.mountain.getPlayer());
    	assertFalse(this.mountain.isBusy());
   
    }
    
    @Test
    public void getRessourceTest() {
    	Resource rock = this.mountain.getRessource(3);
    	assertNotNull(rock);
    	assertFalse(rock instanceof Sand);
    	assertTrue(rock.getValue()==3);
    	assertFalse(rock.getValue()==6);
    	assertFalse(rock instanceof Wood);
    	assertTrue(rock instanceof Rock);
    	assertTrue(rock.getValue()==3);
    }
    @Test
    public void toStringTest() {
    	assertEquals(this.mountain.toString(), "M ");
    }
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.tile.MountainTest.class
        );
    }
}
	
	