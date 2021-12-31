package Trab2.grupo1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static Trab2.grupo1.StreamUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReplace {
    @Test
    public void testWithTwoDigits() throws IOException {
        StringReader sr = new StringReader( "No dia 12 de Janeiro" );
        StringWriter sw = new StringWriter();
        assertEquals(0, copyWithReplace( sr, sw));
        assertEquals("No dia 12 de Janeiro", sw.toString());
    }

    @Test
    public void testWithThreeDigits() throws IOException {
        StringReader sr = new StringReader( "Eram 123 alunos" );
        StringWriter sw = new StringWriter();
        assertEquals(0, copyWithReplace( sr, sw));
        assertEquals("Eram 123 alunos", sw.toString());
    }

    @Test
    public void testAtTheBegin() throws IOException {
        StringReader sr = new StringReader( "123 alunos" );
        StringWriter sw = new StringWriter();
        assertEquals(0, copyWithReplace( sr, sw));
        assertEquals("123 alunos", sw.toString());
    }

    @Test
    public void testAtTheEnd() throws IOException {
        StringReader sr = new StringReader( "Aula pratica dia 20" );
        StringWriter sw = new StringWriter();
        assertEquals(0, copyWithReplace( sr, sw));
        assertEquals("Aula pratica dia 20", sw.toString());
    }

    @Test
    public void testWithoutReplace() throws IOException {
        String teste = "2ª Trabalho começou em 2/12 para 100 alunos\n"+
                       "Data limite de entrega dia 8/1";
        StringReader sr = new StringReader( teste );
        StringWriter sw = new StringWriter();
        assertEquals(0, copyWithReplace( sr, sw));
        assertEquals(teste, sw.toString());
    }

    @Test
    public void testWithOneDot() throws IOException {
        StringReader sr = new StringReader( "Dia 24 de Dezembro 2021 é numa sexta" );
        StringWriter sw = new StringWriter();
        assertEquals(1, copyWithReplace( sr, sw));
        assertEquals("Dia 24 de Dezembro 2.021 é numa sexta", sw.toString());
     }

    @Test
    public void testWithTwoDot() throws IOException {
        StringReader sr = new StringReader( "1234567" );
        StringWriter sw = new StringWriter();
        assertEquals(2, copyWithReplace( sr, sw));
        assertEquals("1.234.567", sw.toString());
    }

    @Test
    public void testWithOneReplace() throws IOException {
        String teste = "2314 casos, 68538 ativos.\n" +
                "N: 446944; C: 171091; L: 456008; Al: 43698; A: 52375";
        String expected = "2.314 casos, 68.538 ativos.\n" +
                "N: 446.944; C: 171.091; L: 456.008; Al: 43.698; A: 52.375";
        StringReader sr = new StringReader( teste );
        StringWriter sw = new StringWriter();
        assertEquals(7, copyWithReplace( sr, sw));
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testWithTwoReplace() throws IOException {
        String teste = "10153720 tem 1196602 casos, destes 1109391 recuperados";
        String expected = "10.153.720 tem 1.196.602 casos, destes 1.109.391 recuperados";
        StringReader sr = new StringReader( teste );
        StringWriter sw = new StringWriter();
        assertEquals(6, copyWithReplace( sr, sw));
        assertEquals(expected, sw.toString());
    }


    @Test
    public void testGetContent() throws IOException {
        String teste = "2ª Trabalho começou em 2/12/2021 para 100 alunos\n" +
                "2314 casos, 68538 ativos.\n" +
                "10153720 com 1196602 casos em Lisboa 456008\n";
        String expected =
                "2ª Trabalho começou em 2/12/2.021 para 100 alunos\n" +
                "2.314 casos, 68.538 ativos.\n" +
                "10.153.720 com 1.196.602 casos em Lisboa 456.008\n";
        assertEquals(expected, contentWithReplace(teste));
    }

    @Test
    public void testPrintReplace() throws IOException {
        printWithReplace("fileTeste.txt");
        System.out.println("Fim -----------------------");
    }

    @Test
    public void testCopyReplace() throws IOException {
        copyWithReplace("fileTeste.txt",
                       "fileWithReplace.txt");
        System.out.println("Fim -----------------------");
    }

}
