package game.action;
import game.*;
import game.Character;
import game.board.ClassicBoard;
import game.farmGame.FarmPlayer;
import game.farmGame.Farmer;
import game.resource.Corn;
import game.tile.*;
import game.util.exception.*;
import game.warGame.Army;
import game.warGame.WarPlayer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
public class DoNothingTest {
	
	//
	private ClassicBoard board;
	private DoNothing yoni;
	private DeployFarmGame deploy;
	@Before
	public void before() {
		this.yoni = new DoNothing();
		
		
		
		
	}
	@Test
	public void toStringTest() {
		assertTrue(this.yoni.toString(null, null, null, null).equals("This Action is usless"));
	}
	
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.action.DoNothingTest.class
        );
    }
	
}