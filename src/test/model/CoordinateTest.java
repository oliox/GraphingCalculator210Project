package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {
    @Test
    public void constructorTest() {
        Coordinate crd = new Coordinate(3, 2, false);
        assertEquals(3, crd.getPosX());
        assertEquals(2, crd.getPosY());
        assertEquals(false, crd.isImportant());

        crd = new Coordinate(3.14, 42.1, true);
        assertEquals(3.14, crd.getPosX());
        assertEquals(42.1, crd.getPosY());
        assertEquals(true, crd.isImportant());
    }
}
