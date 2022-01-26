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

public class OceanTest{
	private  WarPlayer yoni;
    private Army army;
    private Tile ocean;
    private FarmPlayer isi;
    private Farmer farm;
    
    @Before
	public void before(){
		
		 
		this.ocean = new Ocean(0, 0);
		
	
    }
	@Test 
    public void getPositionTest() {
    	assertTrue(this.ocean.getXposition() == 0);
    	assertTrue(this.ocean.getYposition() == 0);
    }
    @Test
    public void isBusyTest() {
    	assertFalse(this.ocean.isBusy());
    }
    
    @Test
    public void getPlayerTest() {
    	assertNull(this.ocean.getPlayer());
    }
    @Test
    public void getRessourceTest() {
    	assertNull(this.ocean.getRessource(2));
    }
    @Test
    public void toStringTest() {
    	assertEquals(this.ocean.toString(), "O ");
    }
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.tile.OceanTest.class
        );
    }
    
    
}