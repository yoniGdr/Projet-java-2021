package game.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class RockTest {


    @Test
    public void checkIfValueIsCorrectTest() {
        Rock c = new Rock(4);
        assertEquals(4, c.getValue());
        Rock c2 = new Rock(8);
        assertEquals(8, c2.getValue());
    }
    
    @Test
    public void toStringTest(){
        
        Rock c = new Rock(4);
        assertEquals(4, c.getValue());
        assertTrue(c.toString().equals("Rock"));

    }
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.resource.RockTest.class
        );
    }
}
