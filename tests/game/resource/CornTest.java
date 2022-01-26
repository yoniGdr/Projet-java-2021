package game.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class CornTest {


    @Test
    public void checkIfValueIsCorrectTest() {
        Corn c = new Corn(4);
        assertEquals(4, c.getValue());
        Corn c2 = new Corn(8);
        assertEquals(8, c2.getValue());
    }
    
    @Test
    public void toStringTest(){
        
        Corn c = new Corn(4);
        assertEquals(4, c.getValue());
        assertTrue(c.toString().equals("Corn"));

    }
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.resource.CornTest.class
        );
    }
}
