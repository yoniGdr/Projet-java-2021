package game.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class WoodTest {


    @Test
    public void checkIfValueIsCorrectTest() {
        Wood c = new Wood(4);
        assertEquals(4, c.getValue());
        Wood c2 = new Wood(8);
        assertEquals(8, c2.getValue());
    }
    
    @Test
    public void toStringTest(){
        
        Wood c = new Wood(4);
        assertEquals(4, c.getValue());
        assertTrue(c.toString().equals("Wood"));

    }
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.resource.WoodTest.class
        );
    }
}

