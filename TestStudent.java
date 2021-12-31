package Trab2.grupo1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {
    @Test
    public void testgetName() {
        Student s = new Student("Maria Silva  : 49234 6 16");
        assertEquals("Maria Silva", s.getName());
    }

    @Test
    public void testgetNumber() {
        Student s = new Student("Maria Silva  : 49234 6 16");
        assertEquals(49234, s.getNumber());
     }

    @Test
    public void testgetNumberOfCourseUnits() {
        Student s = new Student("Maria Silva  : 49234 6 16");
        assertEquals(6, s.getNumberOfCourseUnits());
    }

    @Test
    public void testgetAverageGrade() {
        Student s = new Student("Maria Silva  : 49234 6 16");
        assertEquals(16, s.getAverageGrade());
    }
    @Test
    public void testEquals() {
        Student s = new Student("Maria Silva  : 49234 6 16");
        assertEquals(s, s);
        assertEquals(s, new Student("Maria Silva  : 49234 6 16"));
        assertEquals(s, new Student("MARIA Silva  : 49234 6 16"));
        assertFalse(s.equals( null ));
        assertFalse(s.equals(new Student("Maria Silvas  : 49234 6 16")));
        assertFalse(s.equals(new Student("Maria Silva  : 49233 6 16")));
        assertFalse(s.equals(new Student("Maria Silva  : 49234 7 16")));
        assertFalse(s.equals(new Student("Maria Silva  : 49234 7 18")));
    }
    @Test
    public void testHash() {
        Student s = new Student("Maria Silva  : 49234 6 16");
        assertEquals(s.hashCode(), (new Student("Maria Silva: 49234 6 16")).hashCode());
        assertEquals(s.hashCode(), (new Student("MARIA SILVA  : 49234 6 16")).hashCode());
    }

    @Test
    public void testCompareTo() {
        Student s = new Student("Maria Silva  :  49234 6 16");
        assertTrue( s instanceof Comparable );
        assertEquals( 0, s.compareTo(new Student("Maria Silva  : 49234 6 16")));
        assertTrue( s.compareTo(new Student("Maria Silva  : 49232 6 15")) > 0);
        assertTrue( s.compareTo(new Student("Maria Silva  : 49235 6 16")) > 0);
        assertTrue( s.compareTo(new Student("Maria Silva  : 49232 6 16")) < 0);
        assertTrue( s.compareTo(new Student("Maria Silva  : 49234 6 17")) < 0);
    }
}
