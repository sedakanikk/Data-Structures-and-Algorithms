package kanik;

import java.util.Scanner;

public abstract class KAbstractGraph implements KGraph {

    // Data Fields
    /** The number of vertices */
    private int numV;
    /** store information of the graph is directed or not */
    private boolean directed;

    // Constructors
    /** Empty constructor
     */
    public KAbstractGraph() {}
    /** Construct a graph with the specified number of vertices and the directed flag.
     * If the directed flag is true, this is a directed graph.
     @param numV - The number of vertices
     @param directed The directed flag
     */
    public KAbstractGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
    }

    // Other methods
    /** Return the number of vertices.
     @return The number of vertices
     */
    public int getNumV() {
        return numV;
    }

    /** Return whether this is a directed graph.
     @return true if this is a directed graph, false if this is an undirected graph
     */
    public boolean isDirected() {
        return directed;
    }

    /** Load the edges of a graph from the array.
     @param array Reading data from array
     */
    public void loadEdgesFromArray(int[][] array) {
        for(int i=0; i<array.length; i++) {
            if(array[i][0] == 0)
                break;
            KEdge edge;
            edge = new KEdge(array[i][0], array[i][1], 1.0);
            insert(edge);
        }
    }

    /** Factory method to create a graph and load the data from an input file to an 2D array which second dimension's length is 2.
     * The first line of the input file should contain the number of people or vertices and number of relation.
     * The remaining lines should contain the edge data as described under loadEdgesFromFile.
     @param file The Scanner that is connected to the file that contains the data
     @param isDirected true if this is a directed graph, false otherwise
     @return KGraph object
     */
    public static KGraph createGraph(Scanner file, boolean isDirected) {

        int number_of_people=0, number_of_ordered_relation=0;

        // number_of_people is numV
        if(file.hasNext())
            number_of_people = Integer.valueOf(file.next());
        if(file.hasNext())
            number_of_ordered_relation = Integer.valueOf(file.next());

        int[][] array;
        if(number_of_people > number_of_ordered_relation)
            array = new int[number_of_people*number_of_people][2];
        else
            array = new int[number_of_ordered_relation*number_of_ordered_relation][2];
        // among the file, assigned values to source and dest
        int size=0;
        while(file.hasNext()) {
            int source = Integer.parseInt(file.next());
            int dest = Integer.parseInt(file.next());
            array[size][0] = source;
            array[size][1] = dest;
            size++;
        }
        // to find transitive things in the array, send function array and its size
        ifTransitive(array, size);
        // to create matrix
        KAbstractGraph returnValue;
        returnValue = new KMatrixGraph(number_of_people, isDirected);
        returnValue.loadEdgesFromArray(array);
        return returnValue;
    }

    /** Checks if the given source and dest pair is already in the array.
     * @param array - Checks inside of the array
     * @param source - The source vertex
     * @param dest - The destination vertex
     * @return True if given pair is in the array, false if it is not
     */
    private static boolean isIn(int[][] array, int source, int dest) {
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array.length; j++) {
                if(array[i][0] == source && array[i][1] == dest)
                    return true;
            }
        }
        return false;
    }

    /** Checks if there is a transitive in the array.
     * @param array - Checks inside of the array
     * @param size - If there is a transitive, adds founded pair to the array
     */
    private static void ifTransitive(int[][] array, int size) {
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array.length; j++) {
                if (array[i][1] == array[j][0] && array[i][0] != array[j][1] && !isIn(array, array[i][0],array[j][1])) {
                    array[size][0] = array[i][0];
                    array[size][1] = array[j][1];
                    size++;
                }
            }
        }
    }
}
