package Trab2;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Character.isDigit;

public class StreamUtils {

    //método auxiliar
    public static int insert(StringBuilder unformated) {
        int i = unformated.length() % 3;
        int count=0;
        if (i == 0) i = 3;

        for (; i < unformated.length(); i += 4) {
            unformated.insert(i, '.');
            count++;
        }
        return count;
    }

    //alinea i
    public static int copyWithReplace(Reader in, Writer pw) throws IOException {

        int count = 0;
        int dotCount=0;
        int aux;
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        String aux1 = "";

        while ((aux = in.read()) != -1) {

            if (isDigit(aux)) {
                sb.append((char)aux);
                count++;
            } else {
                if (count > 0) {
                    dotCount+=insert(sb);
                    pw.append(sb);
                    sb.setLength(0);
                    count = 0;
                }
                pw.write(aux);

            }
        }
        if (count > 0){
            dotCount+=insert(sb);
            pw.append(sb);
        }

        return dotCount;
    }

    //alinea ii
    public static int copyWithReplace(String filenameIn, Writer pw) throws IOException {

        int count =0;

        try(FileReader in = new FileReader(filenameIn)){
            count = copyWithReplace(in , pw);
        }
    return count;
    }
    //alinea iii
    public static void printWithReplace (String filenameIn) throws IOException {

        PrintWriter pw = new PrintWriter(System.out);
        copyWithReplace(filenameIn, pw);
        pw.flush();
    }

    //alinea iv
    public static void copyWithReplace(String filenameIn, String filenameOut) throws IOException{

        try(FileWriter out = new FileWriter(filenameOut)) { //Sempre que se abre/fecha ficheiros deve ser dentro de um try
            copyWithReplace(filenameIn, out);
        }
    }

    //alinea v
    public static String contentWithReplace( String content){
        StringReader in = new StringReader(content);
        StringWriter out = new StringWriter();

        try{
            copyWithReplace(in , out);
        }catch(IOException e){}

        return out.toString();
    }

    public static <V> int forEachIf(BufferedReader in, Function <String,V> buildValue, Predicate<V> pred, Consumer<V> action) throws IOException{

        String line = in.readLine();
        int count = 0;

        while(line != null){
            V value = buildValue.apply(line);
            if (pred.test(value)){
                action.accept(value);
                count ++;
            }
            line = in.readLine();
        }

    }


    public static void main(String[] args) throws IOException{
        //TESTE alinea v
        System.out.println(contentWithReplace("1 2 Prof \n 20 \n 30 Carlos300 Fanisa 3500 Tiago 3450"));
        //TESTE alinea iii
        printWithReplace("src/Trab2/fileIn.txt");
        //TESTE alinea iv
        copyWithReplace("src/Trab2/fileIn.txt","src/Trab2/fileOut.txt" );


        System.out.println("\nFIM");

    }

}
