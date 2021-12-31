package Trab2.grupo1;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static Trab2.grupo1.StreamUtils.forEachIf;

public class TestForEachIf {
    private static String[] phrases = {
            "No dia 12 de Janeiro é a primeira aula presencial Natal",
            "2ª Trabalho começou em 2/12",
            "Data limite de entrega dia 8 de Janeiro"
    };

    @Test
    public void testCopyStrings() throws IOException {
        String allphrases = String.join("\n", phrases )+'\n';
        StringBuilder res = new StringBuilder();
        forEachIf( new BufferedReader(new StringReader( allphrases ) ),
                Function.identity(),
                (s)-> true,
                (s) -> res.append(s+'\n'));
        assertEquals(allphrases, res.toString());
    }
    @Test
    public void testFindFirstLast() throws IOException {
        String allphrases = String.join("\n", phrases )+'\n';
        var res = new Object() { int count;  };
        forEachIf( new BufferedReader(new StringReader( allphrases ) ),
                Function.identity(),
                s -> s.contains("Janeiro"),
                s -> ++res.count);
        assertEquals(2, res.count);
    }

    @Test
    public void testFindMiddle() throws IOException {
        String allphrases = String.join("\n", phrases )+'\n';
        var res = new Object() { int count;  };
        forEachIf( new BufferedReader(new StringReader( allphrases ) ),
                Function.identity(),
                s -> s.contains("2/12"),
                s -> ++res.count);
        assertEquals(1, res.count);
    }

    private StringReader getStreamReaderWith(List<Integer> numbers) {
        StringBuilder lines = new StringBuilder();
        numbers.forEach( v -> lines.append( v +"\n"));
        return new StringReader(lines.toString());
    }

    @Test
    public void testWithCopyOfInteger() throws IOException {
        List<Integer> numbers = List.of(1, 13, 16, 4, 8, 19, 2, 10, 6 );
        ArrayList<Integer> res = new ArrayList<>();
        forEachIf( new BufferedReader(getStreamReaderWith( numbers )),
                Integer::parseInt,
                v -> true,
                v -> res.add( v ));
        assertEquals( numbers, res );
    }

    @Test
    public void testWithCopyParcial() throws IOException {
        List<Integer> expected = List.of(1, 4, 8, 2, 6 );
        List<Integer> numbers = List.of(1, 13, 16, 4, 8, 19, 2, 10, 6 );
        ArrayList<Integer> res = new ArrayList<>();
        forEachIf(new BufferedReader( getStreamReaderWith( numbers ) ),
                Integer::parseInt,
                v -> v < 10,
                res::add);
        assertEquals( expected, res );
    }
}
