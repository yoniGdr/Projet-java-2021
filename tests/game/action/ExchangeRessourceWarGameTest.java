/**
 * 
 */
package game.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.Tile;
import game.board.ClassicBoard;
import game.tile.Forest;
import game.tile.Plain;
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
public class ExchangeRessourceWarGameTest {

	private ClassicBoard board;
	private DeployWarGame yoni;
	private ExchangeRessourceWarGame exchange;
	
	@Before
	public void before(){
		this.board = new ClassicBoard(5,5);
		this.yoni = new DeployWarGame(this.board);
		this.exchange = new ExchangeRessourceWarGame(this.board);
		
	}

	@Test
	public void test() throws Exception {
		WarPlayer isi = new WarPlayer("isi",true );
		WarPlayer yoni = new WarPlayer("yoni",true );
		WarPlayer zack = new WarPlayer("zack",true );
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
		Army armyisi = new Army("les guerriers", tilede, 0, 1, isi);
		this.yoni.realise(armyisi, isi);
		assertTrue(tilede.isBusy());
		
		assertTrue(isi.getDeployedCharacters().size()==1);
		Army army = new Army("les guerriers", tilede1, 0, 3, isi);
		this.yoni.realise(army, isi);
		assertTrue(isi.getFood()==10);
		isi.harvest();
		
		this.exchange.realise(null, isi);
		if (tilede  instanceof Forest || tilede instanceof Plain) {
			assertFalse(isi.getFood()==10);
		}
		assertTrue(isi.getResource().size()==0);
		
		
	}
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.action.ExchangeRessourceWarGameTest.class
        );
    }


}
