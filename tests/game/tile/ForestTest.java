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

public class ForestTest{
	
	
	private  WarPlayer yoni;
    private Army army;
    private Tile forest;
    private FarmPlayer isi;
    private Farmer farm;
    
	@Before
	public void before() throws WrongSizeCharacterException {
		
		 this.isi = new FarmPlayer("isi", true);
        this.yoni = new WarPlayer("Yoni",true);
		this.forest = new Forest(0, 0);
		this.army = new Army("Boss",this.forest,0,5,this.yoni);
	    this.farm = new Farmer(new Desert(0,0),  this.isi);
	}
	@Test 
    public void getPositionTest() {
    	assertTrue(this.forest.getXposition() == 0);
    	assertTrue(this.forest.getYposition() == 0);
    }
    @Test
    public void isBusyTest() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	WarPlayer zack = new WarPlayer("zack", true);
    	assertTrue(!this.forest.isBusy());
    	
    	this.forest.welcomeCharacter(this.army);
    		
    	assertTrue(this.forest.isBusy());
    	assertNotNull(this.forest.getPlayer());
    	assertNotNull(this.forest.getCharacter());
    	this.forest.setPlayer(zack);
    	assertFalse(this.forest.getPlayer()==this.yoni);
    	this.forest.removeCharacter();
    	assertNull(this.forest.getCharacter());
    	assertNull(this.forest.getPlayer());
    	assertFalse(this.forest.isBusy());
    
    }
    
    @Test
    public void isBusyTest2() throws TooManyCharacterforMountain, TooManyCharacterforDesert, WrongSizeCharacterException {
    	FarmPlayer zack = new FarmPlayer("zack", true);
    	assertTrue(!this.forest.isBusy());
    	
    	this.forest.welcomeCharacter(this.farm);
    		
    	assertTrue(this.forest.isBusy());
    	assertNotNull(this.forest.getPlayer());
    	assertNotNull(this.forest.getCharacter());
    	this.forest.setPlayer(zack);
    	assertFalse(this.forest.getPlayer()==this.yoni);
    	this.forest.removeCharacter();
    	assertNull(this.forest.getCharacter());
    	assertNull(this.forest.getPlayer());
    	assertFalse(this.forest.isBusy());
   
    }
    
    @Test
    public void getRessourceTest() {
    	Resource wood = this.forest.getRessource(3);
    	assertNotNull(wood);
    	assertFalse(wood instanceof Sand);
    	assertTrue(wood.getValue()==3);
    	assertTrue(wood instanceof Wood);
    	assertTrue(wood.getValue()==3);
    }
    
    @Test
    public void toStringTest() {
    	assertEquals(this.forest.toString(), "F ");
    }
    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.tile.ForestTest.class
        );
    }
}