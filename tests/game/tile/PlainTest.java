package game.tile;

import static org.junit.Assert.*;

import org.junit.Before;

import game.Character;
import game.Resource;
import game.Tile;
import game.board.ClassicBoard;
import game.farmGame.FarmPlayer;
import game.farmGame.Farmer;
import game.resource.*;

import game.tile.*;
import game.util.exception.*;
import game.warGame.Army;
import game.warGame.WarPlayer;
import junit.framework.TestCase;
import org.junit.Test;

public class PlainTest{
	private  WarPlayer yoni;
    private Army army;
    private Tile plain;
    private FarmPlayer isi;
    private Farmer farm;
    
    @Before
	public void before() throws WrongSizeCharacterException {
		
		 this.isi = new FarmPlayer("isi", true);
        this.yoni = new WarPlayer("Yoni",true);
		this.plain = new Plain(0, 0);
		this.army = new Army("Boss",this.plain,0,5,this.yoni);
	    this.farm = new Farmer(new Desert(0,0),  this.isi);
	
    }
	@Test 
    public void getPositionTest() {
    	assertTrue(this.plain.getXposition() == 0);
    	assertTrue(this.plain.getYposition() == 0);
    }
    @Test
    public void isBusyTest() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	WarPlayer zack = new WarPlayer("zack", true);
    	assertTrue(!this.plain.isBusy());
    	
    	this.plain.welcomeCharacter(this.army);
    	
    	
    	this.army.reduceSize(2);
    	this.plain.welcomeCharacter(this.army);
    	
    	assertTrue(this.plain.isBusy());
    	assertNotNull(this.plain.getPlayer());
    	assertNotNull(this.plain.getCharacter());
    	this.plain.setPlayer(zack);
    	assertFalse(this.plain.getPlayer()==this.yoni);
    	this.plain.removeCharacter();
    	assertNull(this.plain.getCharacter());
    	assertNull(this.plain.getPlayer());
    	assertFalse(this.plain.isBusy());
    
    }
    @Test
    public void isBusyTest3() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	WarPlayer zack = new WarPlayer("zack", true);
    	assertTrue(!this.plain.isBusy());
    	
    	this.plain.welcomeCharacter(this.army);
    	
    	
    		
    	assertTrue(this.plain.isBusy());
    	assertNotNull(this.plain.getPlayer());
    	assertNotNull(this.plain.getCharacter());
    	this.plain.setPlayer(zack);
    	assertFalse(this.plain.getPlayer()==this.yoni);
    	this.plain.removeCharacter();
    	assertNull(this.plain.getCharacter());
    	assertNull(this.plain.getPlayer());
    	assertFalse(this.plain.isBusy());
    
    }
    
    @Test
    public void isBusyTest2() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	FarmPlayer zack = new FarmPlayer("zack", true);
    	assertTrue(!this.plain.isBusy());
    	
    	this.plain.welcomeCharacter(this.farm);
    		
    	assertTrue(this.plain.isBusy());
    	assertNotNull(this.plain.getPlayer());
    	assertNotNull(this.plain.getCharacter());
    	this.plain.setPlayer(zack);
    	assertFalse(this.plain.getPlayer()==this.yoni);
    	this.plain.removeCharacter();
    	assertNull(this.plain.getCharacter());
    	assertNull(this.plain.getPlayer());
    	assertFalse(this.plain.isBusy());
   
    }
    
    @Test
    public void getRessourceTest() {
    	Resource corn = this.plain.getRessource(3);
    	assertNotNull(corn);
    	assertFalse(corn instanceof Sand);
    	assertTrue(corn.getValue()==3);
    	assertFalse(corn.getValue()==6);
    	assertFalse(corn instanceof Wood);
    	assertTrue(corn instanceof Corn );
    	assertTrue(corn.getValue()==3);
    }
    
    public void toStringTest() {
    	assertEquals(this.plain.toString(), "P ");
    }
    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.tile.PlainTest.class
        );
    }
}