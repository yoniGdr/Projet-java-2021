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
public class DeployFarmGameTest {
	
	//
	private ClassicBoard board;
	private DeployFarmGame yoni;
	@Before
	public void before() {
		this.board = new ClassicBoard(5,5);
	
		this.yoni = new DeployFarmGame(this.board);
		
		
		
	}
	@Test
	public void realiseTest() throws TileNotInBoardException, TilenotAvailableException, TooManyCharacterforMountain, NoUsableTileAvailableException, TooManyCharacterforDesert {
		Tile tilede1 = null;
		Tile tilede = null;
		ArrayList<Map<Integer, Integer>> tiles  = this.board.getCoordinates();
		Map<Integer, Integer> tile = tiles.get(0);
		Map<Integer, Integer> tile1 = tiles.get(1);
		for (Map.Entry<Integer, Integer> entry: tile1.entrySet()) {
			tilede1 = this.board.getTile(entry.getKey(), entry.getValue());
			System.out.print(tilede1.toString());
		}
		for (Map.Entry<Integer, Integer> entry: tile.entrySet()) {
			tilede= this.board.getTile(entry.getKey(), entry.getValue());
			System.out.print(tilede.toString());
		}
		assertTrue(!tilede1.isBusy());
		assertTrue(!tilede.isBusy());
		FarmPlayer isi = new FarmPlayer("isi", true);
		FarmPlayer zack = new FarmPlayer("zack", true);
		Farmer farmer = new Farmer(tilede1, isi);
		Farmer farmerzack = new Farmer(tilede, zack);
		this.yoni.realise(farmer, isi);
		this.yoni.realise(farmerzack, zack);
		
		assertTrue(tilede1.isBusy());
		assertEquals(tilede1.getPlayer(), isi);
		assertTrue(tilede.isBusy());
		assertEquals(tilede.getPlayer(), zack);
		assertFalse(tilede.getPlayer().equals(isi));
		assertTrue(farmer.getSize() ==1);
		assertTrue(farmerzack.getSize() ==1);
		
		
	}

	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.action.DeployFarmGameTest.class
        );
    }


}
