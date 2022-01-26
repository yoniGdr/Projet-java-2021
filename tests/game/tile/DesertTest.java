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
import game.tile.*;
import game.util.exception.*;
import game.warGame.Army;
import game.warGame.WarPlayer;
import junit.framework.TestCase;
import org.junit.Test;

public class DesertTest{
	
	
	private  WarPlayer yoni;
    private Army army;
    private Tile desert;
    private FarmPlayer isi;
    private Farmer farm;
    
	@Before
	public void before() throws WrongSizeCharacterException {
		
		 this.isi = new FarmPlayer("isi", true);
        this.yoni = new WarPlayer("Yoni",true);
		this.desert = new Desert(0, 0);
		this.army = new Army("Boss",this.desert,0,5,this.yoni);
	    this.farm = new Farmer(new Desert(0,0),  this.isi);
	}
    @Test 
    public void getPositionTest() {
    	assertTrue(this.desert.getXposition() == 0);
    	assertTrue(this.desert.getYposition() == 0);
    }
    @Test
    public void isBusyTest() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	WarPlayer zack = new WarPlayer("zack", true);
    	assertTrue(!this.desert.isBusy());
    	try {
    		this.desert.welcomeCharacter(this.army);
    		fail("Une exception arait dû être déclenché");
    	}
    	catch(TooManyCharacterforDesert e) {
    		this.army.reduceSize(2);
    		this.desert.welcomeCharacter(this.army);
    	}
    	assertTrue(this.desert.isBusy());
    	assertNotNull(this.desert.getPlayer());
    	assertNotNull(this.desert.getCharacter());
    	this.desert.setPlayer(zack);
    	assertFalse(this.desert.getPlayer()==this.yoni);
    	this.desert.removeCharacter();
    	assertNull(this.desert.getCharacter());
    	assertNull(this.desert.getPlayer());
    	assertFalse(this.desert.isBusy());
    
    }
    
    @Test
    public void isBusyTest2() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	FarmPlayer zack = new FarmPlayer("zack", true);
    	assertTrue(!this.desert.isBusy());
    	
    	this.desert.welcomeCharacter(this.farm);
    		
    	assertTrue(this.desert.isBusy());
    	assertNotNull(this.desert.getPlayer());
    	assertNotNull(this.desert.getCharacter());
    	this.desert.setPlayer(zack);
    	assertFalse(this.desert.getPlayer()==this.yoni);
    	this.desert.removeCharacter();
    	assertNull(this.desert.getCharacter());
    	assertNull(this.desert.getPlayer());
    	assertFalse(this.desert.isBusy());
    }
    @Test
    public void getRessourceTest() {
    	Resource sand = this.desert.getRessource(3);
    	assertNotNull(sand);
    	assertTrue(sand instanceof Sand);
    	assertTrue(sand.getValue()==3);
    }
    @Test
    public void toStringTest() {
    	assertEquals(this.desert.toString(), "D ");
    }
    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.tile.DesertTest.class
        );
    }
    
}