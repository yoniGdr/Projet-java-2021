package game.action;
import game.*;
import game.Character;
import game.board.ClassicBoard;
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
public class DeployWarGameTest {
	
	//
	private ClassicBoard board;
	private DeployWarGame yoni;
	@Before
	public void before() {
		this.board = new ClassicBoard(5,5);
	
		this.yoni = new DeployWarGame(this.board);
		
		
		
	}
	
	
	
	
	
	
	@Test
	public void realiseTest() throws WrongSizeCharacterException, TooManyCharacterforMountain, TooManyCharacterforDesert, TilenotAvailableException {
		
		WarPlayer isi = new WarPlayer("isi",true );
		WarPlayer yoni = new WarPlayer("yoni",true );
		WarPlayer zack = new WarPlayer("zack",true );
		Tile tilede = null;
		Tile tilede1 = null;
		ArrayList<Map<Integer, Integer>> tiles  = this.board.getCoordinates();
		Map<Integer, Integer> tile = tiles.get(0);
		Map<Integer, Integer> tile1 = tiles.get(1);
		for (Map.Entry<Integer, Integer> entry: tile.entrySet()) {
			tilede = this.board.getTile(entry.getKey(), entry.getValue());
			System.out.print(tilede.toString());
		}
		for (Map.Entry<Integer, Integer> entry: tile1.entrySet()) {
			tilede1 = this.board.getTile(entry.getKey(), entry.getValue());
			System.out.print(tilede1.toString());
		}
		
		assertFalse(tilede.isBusy());
		Army armyisi = new Army("les guerriers", tilede, 0, 1, isi);
		this.yoni.realise(armyisi, isi);
		assertTrue(tilede.isBusy());
		
		assertTrue(isi.getDeployedCharacters().size()==1);
		Army army = new Army("les guerriers", tilede1, 0, 3, isi);
		assertTrue(army.getGold()==0);
		this.yoni.realise(army, yoni);
		assertTrue(tilede1.isBusy());
		if (this.board.isAdjacent(tilede.getXposition(), tilede.getYposition(), tilede1.getXposition(), tilede1.getYposition())) {
			assertTrue(isi.getDeployedCharacters().size()==0);
			assertTrue(army.getGold()==2);
		}
		
				
				
	}
	@Test
	public void realiseTest1() throws WrongSizeCharacterException, TooManyCharacterforMountain, TooManyCharacterforDesert, TilenotAvailableException {
		WarPlayer isi = new WarPlayer("isi",true );
		WarPlayer yoni = new WarPlayer("yoni",true );
		WarPlayer zack = new WarPlayer("zack",true );
		Tile tilede = null;
		Tile tilede1 = null;
		Tile tilede2 = null;
		ArrayList<Map<Integer, Integer>> tiles  = this.board.getCoordinates();
		Map<Integer, Integer> tile = tiles.get(0);
		Map<Integer, Integer> tile1 = tiles.get(1);
		Map<Integer, Integer> tile2 = tiles.get(2);
		for (Map.Entry<Integer, Integer> entry: tile.entrySet()) {
			tilede = this.board.getTile(entry.getKey(), entry.getValue());
			System.out.print(tilede.toString());
		}
		for (Map.Entry<Integer, Integer> entry: tile1.entrySet()) {
			tilede1 = this.board.getTile(entry.getKey(), entry.getValue());
			System.out.print(tilede1.toString());
		}
		for (Map.Entry<Integer, Integer> entry: tile2.entrySet()) {
			tilede2 = this.board.getTile(entry.getKey(), entry.getValue());
			System.out.print(tilede2.toString());
		}
		Army armyisi = new Army("les guerriers", tilede, 0, 5, isi);
		if (tilede instanceof Mountain) {
			try {
				this.yoni.realise(armyisi, isi);
				
				fail("Une exception devrait être lancé");
			}
			catch(TooManyCharacterforMountain e) {
				armyisi = new Army("les guerriers", tilede, 0, 3, isi);
				this.yoni.realise(armyisi, isi);
			}
		}
		else if (tilede instanceof Desert) {
			try {
				this.yoni.realise(armyisi, isi);
				
				fail("Une exception devrait être lancé");
			}
			catch(TooManyCharacterforDesert e) {
				armyisi = new Army("les guerriers", tilede, 0, 3, isi);
				this.yoni.realise(armyisi, isi);
			}
		}else {this.yoni.realise(armyisi, isi);
		}
		assertTrue(tilede.isBusy());
		Army armyisi1 = new Army("les guerriers", tilede1, 0, 1, isi);
		
		Army armyisi2 = new Army("les guerriers", tilede2, 0, 3, isi);
		this.yoni.realise(armyisi1, isi);
		if (this.board.isAdjacent(tilede1.getXposition(), tilede1.getYposition(), tilede2.getXposition(), tilede2.getYposition())) {
			int gold = armyisi2.getGold();
			int armysize = armyisi1.getSize();
			this.yoni.realise(armyisi2, isi);
			assertTrue(tilede2.isBusy());
			
			assertTrue(armyisi1.getSize()==armysize+1);
			assertTrue(armyisi2.getGold()==gold+1);
		}
		assertTrue(tilede1.isBusy());
		
	}
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.action.DeployWarGameTest.class
        );
    }
	
}