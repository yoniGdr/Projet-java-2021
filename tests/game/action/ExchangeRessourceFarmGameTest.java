/**
 * 
 */
package game.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.Tile;
import game.board.ClassicBoard;
import game.farmGame.FarmPlayer;
import game.farmGame.Farmer;
import game.tile.Desert;
import game.tile.Forest;
import game.tile.Mountain;
import game.tile.Plain;
import game.util.exception.NoUsableTileAvailableException;
import game.util.exception.TileNotInBoardException;
import game.util.exception.TilenotAvailableException;
import game.util.exception.TooManyCharacterforDesert;
import game.util.exception.TooManyCharacterforMountain;
import game.util.exception.WrongSizeCharacterException;
import game.warGame.Army;
import game.warGame.WarPlayer;

/**
 * @author amevigbe
 *
 */
public class ExchangeRessourceFarmGameTest {

	
	private ClassicBoard board;
	private DeployFarmGame yoni;
	private ExchangeRessourceFarmGame exchange;
	
	@Before
	public void before(){
		this.board = new ClassicBoard(5,5);
		this.yoni = new DeployFarmGame(this.board);
		this.exchange = new ExchangeRessourceFarmGame();
	}
	@Test
	public void test() throws Exception {
		FarmPlayer isi = new FarmPlayer("isi",true );
		FarmPlayer yoni = new FarmPlayer("yoni",true );
		FarmPlayer zack = new FarmPlayer("zack",true );
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
		assertFalse(tilede.isBusy());
		Farmer farmisi = new Farmer(tilede, isi);
		this.yoni.realise(farmisi, isi);
		
		assertTrue(tilede.isBusy());
		assertTrue(isi.getDeployedCharacters().size()==1);
		Farmer  farmer = new Farmer(tilede1, isi);
		
		assertTrue(isi.getGold()==15);
		
		isi.harvest();
		
		this.exchange.realise(null, isi);
		
		if (tilede  instanceof Mountain ) {
			System.out.println("1");
			System.out.println(isi.getGold());
			assertTrue(isi.getGold()==23);
		}
		else if (tilede instanceof Desert) {
			System.out.println("2");
			System.out.println(isi.getGold());
			assertTrue(isi.getGold()==20);
		}
		else {
			System.out.println("3");
			System.out.println(isi.getGold());
			assertTrue(isi.getGold()==17);
		}
	isi.removeDeployedCharacter(farmisi);
		int res = isi.getGold();
		assertTrue(isi.getResource().size()==0);
		this.yoni.realise(farmer, isi);
		isi.harvest();
		System.out.println((isi).showResources());
		this.exchange.realise(null, isi);
		System.out.println(res);
		if (tilede1  instanceof Mountain ) {
			System.out.println("1");
			System.out.println(isi.getGold());
			assertTrue(isi.getGold()==res+8);
		}
		else if (tilede1 instanceof Desert) {
			System.out.println("2");
			System.out.println(isi.getGold());
			assertTrue(isi.getGold()==res+5);
		}
		else  {
			System.out.println("3");
			System.out.println(isi.getGold());
			assertTrue(isi.getGold()==res+2);
		}
		assertTrue(isi.getResource().size()==0);
		
		
	}
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.action.ExchangeRessourceFarmGameTest.class
        );
    }
	}

