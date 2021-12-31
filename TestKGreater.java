package Trab2.grupo1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TestKGreater {
    private static String FILENAME = "semesters\\semester";
    private static String DIRNAME = "students";

    private static ArrayList<Student> load(int semester) {
        ArrayList<Student> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME + semester + ".txt"))) {
            StreamUtils.forEachIf(br, Student::new, st -> true, result::add );
        } catch (IOException e) {
            throw new NoSuchElementException();
        }
        return result;
    }

    private static List<Student> semester5= load(5);
    private static List<Student> semester3= load(3);

    @Test
    public void testKGreaterAll() throws IOException {
        Student[] greater = Student.getKGreater(semester5.size(), 5, DIRNAME);
        assertEquals(semester5.size(), greater.length);
        assertEquals(semester5, Arrays.asList(greater));

        greater = Student.getKGreater(semester3.size(), 3, DIRNAME);
        assertEquals(semester3.size(), greater.length);
        assertEquals(semester3, Arrays.asList(greater));
    }

    @Test
    public void testKGreaterGreaterThanTotal() throws IOException {
        Student[] greater= Student.getKGreater(semester5.size()+10, 5, DIRNAME);
        assertEquals( semester5.size(), greater.length );
        assertEquals(semester5, Arrays.asList( greater ));

        greater= Student.getKGreater(semester3.size()+5, 3, DIRNAME);
        assertEquals( semester3.size(), greater.length );
        assertEquals(semester3, Arrays.asList( greater ));

    }

    @Test
    public void testKGreaterLessThanTotal() throws IOException {
        Student[] greater= Student.getKGreater(10, 5, DIRNAME);
        assertEquals( 10, greater.length );
        assertEquals(semester5.subList(0, 10), Arrays.asList( greater ));
        greater= Student.getKGreater(5, 3, DIRNAME);
        assertEquals( 5, greater.length );
        assertEquals( semester3.subList(0, 5), Arrays.asList( greater ) );
    }
}
