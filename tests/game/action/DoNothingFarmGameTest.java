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
public class DoNothingFarmGameTest {
	
	//
	private ClassicBoard board;
	private DoNothingFarmGame yoni;
	private DeployFarmGame deploy;
	@Before
	public void before() {
		this.board = new ClassicBoard(5,5);
	
		this.yoni = new DoNothingFarmGame();
		this.deploy = new DeployFarmGame(this.board);
		
		
		
	}
	@Test
	public void realiseTest() throws Exception {
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
		assertTrue(isi.getGold() == 15);
		assertTrue(zack.getGold() == 15);
		this.deploy.realise(farmer, isi);
		this.deploy.realise(farmerzack, zack);
		
		this.yoni.realise(farmer, isi);
		this.yoni.realise(farmerzack, zack);
		
		if (tilede1 instanceof Forest || tilede1 instanceof Plain) {
			
			assertTrue(isi.getGold() == 16);
		}
		if (tilede1 instanceof Desert) {
			
			assertTrue(isi.getGold() == 17);
		}
		if (tilede instanceof Forest || tilede instanceof Plain) {
			
			assertTrue(zack.getGold() == 16);
		}
		if (tilede instanceof Desert) {
			
			assertTrue(zack.getGold() == 17);
		}
		
		
		
	}
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.action.DoNothingFarmGameTest.class
        );
    }




}