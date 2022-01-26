package game.farmGame;
import game.*;
import game.Character;
import game.tile.*;
import game.util.exception.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class FarmerTest {
	
    private  FarmPlayer yo;
	private Tile mountain;
	private Tile desert;
	private Farmer Zak;
 
	
	@Before
	public void before() {
		this.yo = new FarmPlayer("Yoni",true);
		
        this.mountain = new Mountain(0, 0);
        this.desert = new Desert(0, 0);
        this.Zak = new Farmer(this.mountain,this.yo);
	}
	
	
	
    @Test
	public void testgetPayed() throws Exception {
		assertEquals(yo.getGold(),15);
		assertSame(this.Zak.getOwner(), this.yo);
		try {
			assertTrue(this.Zak.getPayed(20)==8);
		}
		catch (NotEnoughGoldException e){
			fail();}
		Character farmer = this.yo.createCharcater(this.desert, 5);
		assertSame(farmer.getOwner(), this.yo);
		try {
			
			assertTrue(farmer.getPayed(4)==3);
			assertTrue(farmer.getPayed(6)==3);
		}
		catch (NotEnoughGoldException e){
			fail();
	}
    

}
    @Test
    public void reduceSizeTest() {
    	assertTrue(this.Zak.getSize()==1);
    	this.Zak.reduceSize(1);
    	assertTrue(this.Zak.getSize()==0);
    	
    }
    @Test
    public void addGoldTest() {
    	assertTrue(this.yo.getGold()== 15);
    	this.yo.addGold(10);
    	assertTrue(this.yo.getGold() == 25);
    	
    }
    @Test
    public void addSizeTest() {
    	assertTrue(this.Zak.getSize()==1);
    	this.Zak.addSize(5);
    	assertTrue(this.Zak.getSize()==6);
    }
    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.farmGame.FarmerTest.class
        );
    }
    

}