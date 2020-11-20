package kanik;

public class KPopularSearch {

    // Data field
    /** Stores the weight number 1.0 of the graph weight */
    private int[] array;

    // Constructor
    /** Constructor to initialize array.
     * @param size - To allocate by size
     */
    public KPopularSearch(int size) {
        array = new int[size];
    }

    // Other methods
    /** Finds the number of popular vertex or vertices in the graph.
     * @param graph - Finds popular vertex or vertices in this graph
     * @return The number of popular vertex or vertices
     */
    public int findPopular(KGraph graph) {
        // assigned zero all the elements of the array
        for(int i = 0; i< graph.getNumV(); i++) {
            array[i] = 0;
        }
        // if specified edge's weight is 1.0, increase the array's count of this vertex
        for(int i = 1; i<= graph.getNumV(); i++) {
            for(int j = 1; j<= graph.getNumV(); j++) {
                if(graph.getEdge(i,j).getWeight() == 1.0) {
                    array[j-1] += 1;
                }
            }
        }
        // checks all the element of the array to find maximum count
        int max=-1, number_of_popular=0;
        for(int i = 0; i< graph.getNumV(); i++) {
            if(max < array[i]) {
                max = array[i];
            }
        }
        // checks number of vertex which has the maximum count
        for(int i = 0; i< graph.getNumV(); i++) {
            if(max == array[i]) {
                max = array[i];
                number_of_popular++;
            }
        }
        // returns the number of popular vertex
        return number_of_popular;
    }
}
