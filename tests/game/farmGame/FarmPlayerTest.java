package game.farmGame;
import game.*;
import game.Character;
import game.resource.Corn;
import game.tile.*;
import game.util.exception.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class FarmPlayerTest {
	
	//
	private  FarmPlayer yoni;
	private Tile desert;
    private Tile forest;
	private Tile mountain;
	private Tile ocean;
	private Tile plain;
	private Farmer karaD;
	private Farmer karaF;
	private Farmer karaM;
	private Farmer karaO;
	private Farmer karaP; 
	
	@Before
	public void before() {
		this.yoni = new FarmPlayer("Yoni",true);
		
		this.forest = new Forest(1,1);
		this.desert = new Desert(1,1);	
	    this.ocean = new Ocean(1,2);
	    this.plain = new Plain(2,3);
	    this.mountain = new Mountain(1,1);
	    this.karaD = new Farmer(this.desert, yoni);
		this.karaF = new Farmer(this.forest,yoni);
		this.karaM = new Farmer(this.mountain,yoni);
		this.karaO = new Farmer(this.ocean,yoni);
		this.karaP = new Farmer(this.plain,yoni);
		
	}
    @Test
    public void testGetName() {
	   assertEquals(this.yoni.getName(),"Yoni");
	   }
	@Test
	public void testGetGold() {
		assertEquals(this.yoni.getGold(),15);
	    this.yoni.addGold(5);
        assertEquals(this.yoni.getGold(),20);
	}
	
    @Test
    public void testpay() throws NotEnoughGoldException {
    	assertEquals(this.yoni.getGold(),15);
		this.yoni.pay(karaD);
		System.out.println(this.yoni.getGold());
		assertEquals(this.yoni.getGold(),12);
		
        this.yoni.pay(karaF);
        assertEquals(this.yoni.getGold(),11);
        
	    this.yoni.pay(karaM);
		assertEquals(this.yoni.getGold(),3);
		
		this.yoni.pay(karaP);
		assertEquals(this.yoni.getGold(),2);
		
		
		try{
			this.yoni.pay(karaD);
			fail("Une exception aurait dû être levée");
		} 
		catch (NotEnoughGoldException e) {
		}
	}
    
    
	// test add charachter in deployedCharacter and remove character
	@Test
	public void AddAndRemoveCharacter() {
		assertFalse(this.yoni.getDeployedCharacters().contains(karaD));
		this.yoni.adddeployedCharacter(karaD);
		assertTrue(yoni.getDeployedCharacters().contains(karaD));
	    this.yoni.removeDeployedCharacter(karaD);
		assertFalse(yoni.getDeployedCharacters().contains(karaD));
	}
	

	@Test
	public void testcreateCharcater() {
		Character f = this.yoni.createCharcater(desert, 1);
		assertTrue(f.getPosition() instanceof Desert);
		assertEquals(f.getSize(),1);
	}
	   
	@Test
	public void testaddGold(){
		assertEquals(this.yoni.getGold(),15);
		this.yoni.addGold(10);
		assertEquals(this.yoni.getGold(),25);
	}
	
	@Test
	public void testaddPoints(){
		assertEquals(this.yoni.getPoints(),0);
		this.yoni.addPoints(10);
		assertEquals(this.yoni.getPoints(),10);
	}
	
	 
	
	// Test de add ressource + remove + remove all ( a compl�ter ! )
	@Test 
	public void testaddRessource(){
		Resource r = new Corn(1);
		assertFalse(this.yoni.getResource().containsKey(r));
		yoni.addRessource(r);
        assertTrue(this.yoni.getResource().containsKey(r));
		Resource r2 = new Corn(1);
		yoni.addRessource(r2);
		assertTrue(this.yoni.getResource().containsKey(r2));
		//a completer
	    yoni.removeAllResource();
	    assertFalse(this.yoni.getResource().containsKey(r));
		assertFalse(this.yoni.getResource().containsKey(r2));
		assertTrue(this.yoni.getResource().isEmpty());
	}
	
	@Test 
    public void testaddRessourceWithValue(){
		Resource r = new Corn(3);
		assertFalse(this.yoni.getResource().containsKey(r));
		yoni.addRessourcewithValue(r, 3);
	  assertTrue(this.yoni.getResource().containsKey(r));
	  assertEquals((int)this.yoni.getResource().get(r),3);
	}
	
    
	@Test
	public void testisInterfactive(){
		assertEquals(yoni.isInterfactive(),true);
    
	}
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.farmGame.FarmPlayerTest.class
        );
    }
	
	

	

}
