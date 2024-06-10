package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    Exercise e1;
    Exercise e2;
    Exercise e3;
    Sets s1;
    Sets s2;
    Sets s3;

    @BeforeEach
    void before() {
        e1 = new Exercise("bench press");
        e2 = new Exercise("squat");
        e3 = new Exercise("leg press");
        s1 = new Sets(10,10);
        s2 = new Sets(1,1);
        s3 = new Sets(100,1);

    }


    @Test
    void testConstructor() {
        assertEquals("bench press", e1.getName());
        assertEquals(0,e1.length());
        assertEquals(0,e1.getOneRepMax());
        assertEquals("squat", e2.getName());
        assertEquals(0,e1.length());
        assertEquals(0,e1.getOneRepMax());
    }

    @Test
    void testAddSet() {
        List<Sets> l1 = new ArrayList<>();
        e1.addSet(s1);
        l1.add(s1);
        assertEquals(l1,e1.getLog());
        assertEquals(l1,e1.getSortedLog());
        assertEquals(s1.calcOneRepMax(),e1.getOneRepMax());
        e1.addSet(s2);
        l1.add(s2);
        assertEquals(l1,e1.getLog());
        assertEquals(s1.calcOneRepMax(),e1.getOneRepMax());
        e1.addSet(s3);
        l1.add(s3);
        assertEquals(l1,e1.getLog());
        assertEquals(s3.calcOneRepMax(),e1.getOneRepMax());
        Collections.sort(l1);
        assertEquals(l1,e1.getSortedLog());
    }



    @Test
    void testRemoveSet() {
        e1.addSet(s1);
        e1.addSet(s2);
        e1.addSet(s3);
        e1.removeSet(0);
        List<Sets> l1 = new ArrayList<>();
        l1.add(s2);
        l1.add(s3);
        assertEquals(l1,e1.getLog());
        assertEquals(l1,e1.getSortedLog());
        assertEquals(s3.calcOneRepMax(),e1.getOneRepMax());
        e1.removeSet(1);
        l1.remove(1);
        assertEquals(l1,e1.getLog());
        assertEquals(l1,e1.getSortedLog());
        assertEquals(s2.calcOneRepMax(),e1.getOneRepMax());
        e1.removeSet(0);
        l1.remove(0);
        assertEquals(l1,e1.getLog());
        assertEquals(l1,e1.getSortedLog());
        assertEquals(0,e1.getOneRepMax());
        e2.addSet(s1);
        e2.addSet(s2);
        e2.addSet(s3);
        e2.addSet(s1);
        e2.addSet(s2);
        e2.removeSet(0);
        assertEquals(s3.calcOneRepMax(),e2.getOneRepMax());
    }

    @Test
    void removeSetInputSet() {
        e1.addSet(s1);
        e1.addSet(s2);
        e1.addSet(s3);
        e1.removeSet(s1);
        List<Sets> l1 = new ArrayList<>();
        l1.add(s2);
        l1.add(s3);
        assertEquals(l1,e1.getLog());
        assertEquals(l1,e1.getSortedLog());
        assertEquals(s3.calcOneRepMax(),e1.getOneRepMax());
        e1.removeSet(s2);
        l1.remove(0);
        assertEquals(l1,e1.getLog());
        assertEquals(l1,e1.getSortedLog());
        assertEquals(s3.calcOneRepMax(),e1.getOneRepMax());
        e1.removeSet(s3);
        l1.remove(0);
        assertEquals(l1,e1.getLog());
        assertEquals(l1,e1.getSortedLog());
        assertEquals(0,e1.getOneRepMax());
        e2.addSet(s1);
        e2.addSet(s2);
        e2.addSet(s3);
        e2.addSet(s2);
        e2.removeSet(s1);
        assertEquals(s3.calcOneRepMax(),e2.getOneRepMax());
    }

    @Test
    void testRemoveSetSameOneRepMax() {
        e1.addSet(s1);
        e1.addSet(s1);
        List<Sets> l1 = new ArrayList<>();
        l1.add(s1);
        l1.add(s1);
        e1.removeSet(0);
        l1.remove(0);
        assertEquals(l1,e1.getLog());
        assertEquals(s1.calcOneRepMax(),e1.getOneRepMax());
        Sets s0 = new Sets(0,0);
        e2.addSet(s0);
        e2.addSet(s0);
        e2.removeSet(0);
        assertEquals(0,e2.getOneRepMax());
    }

    @Test
    void testLength() {
        e1.addSet(s1);
        assertEquals(1,e1.length());
        e1.addSet(s2);
        assertEquals(2,e1.length());
        e1.removeSet(1);
        assertEquals(1,e1.length());
    }



}
