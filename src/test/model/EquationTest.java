package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquationTest {
    private Equation eqn;

    @BeforeEach
    public void setup() {
        eqn = new Equation("y=2x+1", 10);
    }

    @Test
    public void testFalseIsValid() {
        eqn = new Equation("y=bx+1", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("y=2x+a", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("y=1", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("yasdb", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("y=2a", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("y=2x+", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("y=2xa", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("a", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("", 10);
        assertFalse(eqn.isValid());

    }

    @Test
    public void testDecimalIsValid() {
        eqn = new Equation("y=0.5x", 10);
        assertTrue(eqn.isValid());
        eqn = new Equation("y=0.555x", 10);
        assertTrue(eqn.isValid());
    }

    @Test
    public void testDecimalIsNotValid() {
        eqn = new Equation("y=0..5x", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("y=0.a5x", 10);
        assertFalse(eqn.isValid());
        eqn = new Equation("y=0.5.5x", 10);
        assertFalse(eqn.isValid());
    }

    @Test
    public void testGetXatY() {
        eqn = new Equation("y=2x+1", 10);
        assertEquals(3, eqn.getXAtY(7));
        eqn = new Equation("y=x", 10);
        assertEquals(6, eqn.getXAtY(6));
    }

    @Test
    public void testTrueIsValid() {
        assertTrue(eqn.isValid());
        eqn = new Equation("y=251x+1098", 10);
        assertTrue(eqn.isValid());
        eqn = new Equation("y=x", 10);
        assertTrue(eqn.isValid());
    }

    @Test
    public void constructorTest() {
        assertEquals("y=2x+1", eqn.getEquation());

        assertEquals(5, eqn.getValAt(2));
        for (int i = 0; i < eqn.getCoordList().size(); i++) {
            assertEquals(i, eqn.getCoordList().get(i).getPosX());
            assertEquals(2 * i + 1, eqn.getCoordList().get(i).getPosY());
        }

        eqn = new Equation("y=251x+1098", 10);
        assertEquals(251 * 2 + 1098, eqn.getValAt(2));
        assertEquals(251, eqn.getCoefficient());
        for (int i = 0; i < eqn.getCoordList().size(); i++) {
            assertEquals(i, eqn.getCoordList().get(i).getPosX());
            assertEquals(251 * i + 1098, eqn.getCoordList().get(i).getPosY());
        }

        eqn = new Equation("y=x", 10);
        assertEquals(1, eqn.getCoefficient());
        assertEquals(2, eqn.getValAt(2));
        for (int i = 0; i < eqn.getCoordList().size(); i++) {
            assertEquals(i, eqn.getCoordList().get(i).getPosX());
            assertEquals(i, eqn.getCoordList().get(i).getPosY());
        }
    }

    @Test
    public void getValAtTest() {
        assertEquals(5, eqn.getValAt(2));
        eqn = new Equation("y=x", 10);
        assertEquals(2, eqn.getValAt(2));
    }
}

