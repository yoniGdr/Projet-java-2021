package game.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class SandTest {


    @Test
    public void checkIfValueIsCorrectTest() {
        Sand c = new Sand(4);
        assertEquals(4, c.getValue());
        Sand c2 = new Sand(8);
        assertEquals(8, c2.getValue());
    }
    
    @Test
    public void toStringTest(){
        
        Sand c = new Sand(4);
        assertEquals(4, c.getValue());
        assertTrue(c.toString().equals("Sand"));

    }
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            game.resource.SandTest.class
        );
    }
}
