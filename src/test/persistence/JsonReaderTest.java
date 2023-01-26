package persistence;

import model.Equation;
import model.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;


public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/doesntexist.json");
        try {
            reader.read();
            fail();
        } catch (IOException e) {
            //good
        }
    }

    void testReaderEmptyEquationList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEquationList.json");
        try {
            ArrayList<Equation> eq = reader.read();
            assertEquals(0, eq.size());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testReaderGeneralEquationList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralEquationList.json");
        try {
            ArrayList<Equation> eq = reader.read();
            assertEquals(2, eq.size());
            assertEquals(true, eq.get(0).isValid());
            assertEquals(3, eq.get(1).getValAt(3));
        } catch (IOException e) {
            fail();
        }
    }
}
