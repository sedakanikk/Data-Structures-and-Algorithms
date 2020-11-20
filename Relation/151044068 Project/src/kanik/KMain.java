package kanik;

import java.io.*;
import java.util.Scanner;

public class KMain {

    public static void main(String[] args) throws IOException {
        // input file read
        Scanner file = new Scanner( new File("input.txt") );
        KGraph graph;
        // file send this function to read every element of file and creating matrix
        graph = KAbstractGraph.createGraph(file, true);
        System.out.println("\nMatrix: ");
        // prints matrix
        System.out.println(graph.toString());

        KPopularSearch popular;
        popular = new KPopularSearch(graph.getNumV());
        int number = popular.findPopular(graph);
        System.out.println("Number of popular: ");
        // prints the number of popular
        System.out.println(number);
    }
}
