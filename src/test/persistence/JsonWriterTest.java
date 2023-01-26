package persistence;

import model.Equation;
import model.Graph;
import org.junit.jupiter.api.Test;
import ui.GraphDrawer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Graph eq = new Graph(GraphDrawer.DEFAULT_RANGE, GraphDrawer.DEFAULT_RANGE);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail();
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyEquationList() {
        try {
            Graph eq = new Graph(GraphDrawer.DEFAULT_RANGE, GraphDrawer.DEFAULT_RANGE);
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyEquationList.json");
            writer.open();
            writer.write(eq);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyEquationList.json");
            eq.setEquationList(reader.read());
            assertEquals(0, eq.getEquationList().size());
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralEquationList() {
        try {
            Graph eq = new Graph(GraphDrawer.DEFAULT_RANGE, GraphDrawer.DEFAULT_RANGE);
            eq.addEquation(new Equation("y=3x+1", 10));
            eq.addEquation(new Equation("y=x", 10));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralEquationList.json");
            writer.open();
            writer.write(eq);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralEquationList.json");
            eq.setEquationList(reader.read());
            assertEquals(2, eq.getEquationList().size());
            assertEquals(true, eq.getEquationList().get(0).isValid());
            assertEquals(3, eq.getEquationList().get(1).getValAt(3));
        } catch (IOException e) {
            // pass
        }
    }
}
