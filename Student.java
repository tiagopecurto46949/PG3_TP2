package Trab2.grupo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static Trab2.grupo1.StreamUtils.forEachIf;

public class Student implements Comparable<Student>{

    private String name;
    private int number, nrCoursesUnits, averageGrade;

    public Student(String fullDescription){
        int index1 = fullDescription.indexOf(":",0);
        String name = fullDescription.substring(0,index1).trim();
        String ints = fullDescription.substring(index1+1, fullDescription.length());
        String[] splitted = ints.split(" ");
        number = Integer.parseInt(splitted[0]);
        nrCoursesUnits = Integer.parseInt(splitted[1]);
        averageGrade = Integer.parseInt(splitted[2]);
    }

    public String getName(){return name;}

    public int getNumber(){return number;}

    public int getNumberOfCourseUnits(){return nrCoursesUnits;}

    public int getAverageGrade(){ return averageGrade;}

    public boolean equals( Object o){
        if (o instanceof Student){
            Student other = (Student) o;
            return ((this.name.equals(other.name))  && (this.number == other.number) && (this.nrCoursesUnits == other.nrCoursesUnits) &&(this.averageGrade == other.averageGrade));
        }
        return false;
    }

    public String toString(){
        return getName() + " : " + getNumber() + " " + getNumberOfCourseUnits() + " " + getAverageGrade();
    }

    public int hashCode(){
        return Objects.hash(name,number,nrCoursesUnits,averageGrade);
    }

    @Override
    public int compareTo(Student student) {

        if(this.getAverageGrade() == student.getAverageGrade()) return student.getNumber() - this.getNumber();

            //o resultado do compareTo tem de dar positivo se o número de aluno do this for menor

        return this.getAverageGrade() - student.getAverageGrade();
    }

    public static Student[] getKGreater(int k, int semester, String dirName) throws IOException {

        PriorityQueue<Student> topStud = new PriorityQueue<>();

        Predicate<Student> pred = student -> student.getNumberOfCourseUnits() > 5;
        Function<String, Student> func = description -> new Student(description);

        Consumer<Student> cons = student -> {
            if (topStud.size() < k) {
                topStud.add(student);
            } else if (topStud.peek().compareTo(student) < 0) {
                topStud.poll();
                topStud.add(student);
            }
        };

        File dir = new File(dirName);
        if (dir.isDirectory()) {

            File[] files = dir.listFiles(); //Array de files

            for (File f : files) {
                int index = f.getName().lastIndexOf('.') - 3;
                if ( f.getName().charAt(index) - '0' == semester) {
                    try (BufferedReader in = new BufferedReader(new FileReader(f))) {
                        forEachIf(in, func, pred, cons);
                    }
                }
            }


        }
        return topStud.toArray(new Student[topStud.size()]);
    }


}
