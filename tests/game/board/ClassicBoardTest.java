package game.board;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.*;
import org.junit.Test;

import game.Character;
import game.Player;
import game.Tile;
import game.board.ClassicBoard;
import game.tile.*;
import game.util.exception.*;

public class ClassicBoardTest {
    
	
	private int width;
	private int height;
	private ClassicBoard board;
	
	@Before
	public void before(){
		this.height = 5;
		this.width = 5;
		this.board = new ClassicBoard(this.width, this.height);
	}
    
	@Test
	public void getTileTest() {
		int x = 0;
		while (x<this.width) {
			int y = 0;
			
			while(y<this.height) {
				Tile tile = this.board.getTile(x, y);
				assertTrue(tile.getXposition()==x);
				assertTrue(tile.getYposition()==y);
				y = y +1 ;	
			}	
			x=x+1;
		}
		//System.out.println(this.width); 
		//System.out.println(this.coordinates.size());
		
		 
	}
	
	@Test
	public void validTileTest() {
		int x = 0;
		while (x<this.width) {
			int y = 0;
			
			while(y<this.height) {
				Tile tile = new Ocean(x+6,y+5);
				Tile tile1 = new Ocean(x,y);
				assertEquals(this.board.validTile(tile),false);
				assertEquals(this.board.validTile(tile1), true);
				y = y +1 ;	
			}	
			x=x+1;
		}
	}
	
	@Test
	public void getnumberofTileTest() {
		assertTrue(this.height*this.width==this.board.getnumberofTile());
	}
	
	@Test
	public void checkIfAllTilesNotBusyTest() {
		int x = 0;
		while (x < this.width) {
			int y = 0;
			while(y< this.height) {
				assertEquals(!this.board.getTile(x, y).isBusy(), true); 
				y = y + 1;
			}
			 x = x+1;
		}
	}
	
	@Test
	public void getWidthTest() {
		assertEquals(this.board.getWidth(),this.width);
	}
	@Test
	public void getHeightTest() {
		assertEquals(this.board.getHeight(),this.height);
	}
	
	@Test
	public void allUsableTilesTest() {
		ArrayList<Tile> a = this.board.allUsableTiles();
		int i =0;
		int x = 0;
		while (x<this.width) {
			int y = 0;
			
			while(y<this.height) {
				Tile tile = this.board.getTile(x, y);
				if (!(tile instanceof Ocean)) {
					i++;
				}
				y = y +1 ;	
			}	
			x=x+1;
		}
		assertEquals(a.size(), i);
	}
	
	@Test
	public void isAdjacentTest() {
		ArrayList<Map<Integer, Integer>> tiles = this.board.generateAdjacentTile(2, 2);
		for (Map<Integer, Integer> tile: tiles) {
			for(Map.Entry<Integer,Integer> entry :  tile.entrySet()){
				boolean res = this.board.isAdjacent(entry.getKey(), entry.getValue(),2, 2); 
				assertTrue(res);
			}
		}
	}
	
	@Test
	public void isAdjacentTest2() {
		int x = 0;
		ArrayList<Map<Integer, Integer>> tiles;
		while (x<this.width) {
			int y = 0;
			
			while(y<this.height) {
				tiles = this.board.generateAdjacentTile(x, y);
				for (Map<Integer, Integer> tile: tiles) {
					for(Map.Entry<Integer,Integer> entry :  tile.entrySet()){
						boolean res = this.board.isAdjacent(entry.getKey(), entry.getValue(),x, y); 
						assertTrue(res);
					}
				}
				y = y +1 ;	
			}
			x=x+1;	
			}	
			
		}
	@Test
	public void getCoordinatesTest() {
		 ArrayList<Map<Integer, Integer>> a = this.board.getCoordinates();
		 int i = 0;
		 int x = 0;
			Tile tiles;
			while (x<this.width) {
				int y = 0;
				
				while(y<this.height) {
					tiles = this.board.getTile(x, y);
					if (!tiles.isBusy() && !(tiles instanceof Ocean) ) {
						i++;
					}
					y = y +1 ;	
				}
				x=x+1;	
				}
			assertEquals(a.size(), i);
	}
	
	
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.board.ClassicBoardTest.class
        );
    }
	
	
    
    
}
    