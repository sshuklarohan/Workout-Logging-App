package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetsTest {

    Sets s1;
    Sets s2;
    Sets s3;

    @BeforeEach
    void runbefore() {
        s1 = new Sets(1,10);
        s2 = new Sets(50,20);
        s3 = new Sets(150,1);
    }

    @Test
    void constructorTest() {

        assertEquals(1,s1.getWeight());
        assertEquals(10,s1.getReps());
        assertEquals(50,s2.getWeight());
        assertEquals(20,s2.getReps());
    }

    @Test
    void testCalcOneRepMax() {
        assertEquals(150,s3.calcOneRepMax());
        assertEquals(106,s2.calcOneRepMax());
        assertEquals(1,s1.calcOneRepMax());
    }
}
