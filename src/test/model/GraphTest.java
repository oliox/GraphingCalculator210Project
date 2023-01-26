package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    Graph eqList;

    @BeforeEach
    public void setup() {
        eqList = new Graph(10, 10);
    }

    @Test
    public void testAddValidEquation() {
        Equation eqn = new Equation("y=2x+1", 10);
        eqList.addEquation(eqn);
        eqList.addEquation(new Equation("y=x", 10));
        assertEquals(2, eqList.getSize());
        assertEquals(eqn, eqList.getEquationList().get(0));
    }

    @Test
    public void testAddInvalidEquation() {
        eqList.addEquation(new Equation("y=a", 10));
        eqList.addEquation(new Equation("y=2x+b", 10));
        assertEquals(0, eqList.getSize());
        assertTrue(eqList.getEquationList().isEmpty());
    }

    @Test
    public void testClear() {
        eqList.addEquation(new Equation("y=2x+1", 10));
        eqList.addEquation(new Equation("y=x", 10));
        eqList.clear();
        assertEquals(0, eqList.getSize());
        assertTrue(eqList.getEquationList().isEmpty());
    }

    @Test
    public void testConstructor() {
        assertEquals(0, eqList.getEquationList().size());
        assertEquals(10, eqList.getGraphXRange());
        assertEquals(10, eqList.getGraphYRange());
    }
}
