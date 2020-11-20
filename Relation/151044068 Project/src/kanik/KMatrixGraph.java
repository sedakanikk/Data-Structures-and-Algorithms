package kanik;

import java.util.Iterator;

public class KMatrixGraph extends KAbstractGraph {

    // Data field
    /** The two dimensional array to represent an edge */
    private double[][] edges;

    // Constructors
    public KMatrixGraph() {
        super();
    }
    /** Construct a graph with the specified number of vertices and directionality.
     @param numV - The number of vertices
     @param directed - The directionality flag
     */
    public KMatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        edges = new double[numV][numV];
        if (!directed) {
            for(int i=0; i!=numV; i++) {
                for(int j=0; j!=numV; j++)
                    edges[i][j] = 0.0;
            }
        }
        else {
            for(int i=0; i!=numV; i++) {
                for(int j=0; j!=i+1; j++)
                    edges[i][j] = 0.0;
            }
        }
    }

    // Other methods
    /** Insert a new edge into the graph.
     @param edge - The new edge
     */
    public void insert(KEdge edge) {
        setEdgeValue(edge.getSource(), edge.getDest(), edge.getWeight());
    }

    /** Determine if an edge exists
     @param source - The source vertex
     @param dest - The destination vertex
     @return true if there is an edge from u to v
     */
    public boolean isEdge(int source, int dest) {
        return 0 != getEdgeValue(source, dest);
    }

    /** Get the edge between two vertices. If an edge does not exist, an KEdge with a weight of 0 is returned.
     @param source - The source
     @param dest - The destination
     @return the edge between these two vertices
     */
    public KEdge getEdge(int source, int dest) {
        return new KEdge(source-1, dest-1, getEdgeValue(source, dest));
    }

    /** Return an iterator to the edges connected to a given vertex.
     * @param source - The source vertex
     * @return an EdgeIterator to the vertices connected to source
     */
    public Iterator<KEdge> edgeIterator(int source) {
        return new Iter(source);
    }

    /** Method to set the value of an edge.
     @param source - The source vertex
     @param dest - The destination vertex
     @param weight - The weight of the edge
     */
    private void setEdgeValue(int source, int dest, double weight) {
        if (isDirected() || source <= dest)
            edges[source-1][dest-1] = weight;
        else
            edges[dest-1][source-1] = weight;
    }

    /** Method to get the value of an edge.
     * @param source - The source vertex
     * @param dest - The destination vertex
     * @return The weight of this edge 1.0 or 0.0 if no edge exists
     */
    private double getEdgeValue(int source, int dest) {
        if (isDirected() | source <= dest)
            return edges[source-1][dest-1];
        else
            return edges[dest-1][source-1];
    }

    /** Return a String representation of the edge
     * @return A String representation of the edge
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                sb.append(edges[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Iter class
    /** An iterator to the edges.
     * An KEdge iterator is similar to an Iterator except that its next method will always return an edge
     */
    private class Iter implements Iterator <KEdge> {
        // Data fields
        /** The source vertex for this iterator */
        private int source;
        /** The current index for this iterator */
        private int index;

        // Constructor
        /** Construct an EdgeIterator for a given vertex
         *  @param source - The source vertex
         */
        public Iter(int source) {
            this.source = source;
            index = 0;
            advanceIndex();
        }

        /** Return true if there are more edges
         *  @return true if there are more edges
         */
        public boolean hasNext() {
            return index != getNumV();
        }

        /** Return the next edge if there is one more edges
         *  @return the next KEdge in the iteration
         */
        public KEdge next() {
            if (index == getNumV()) {
                throw new java.util.NoSuchElementException();
            }
            KEdge returnValue = new KEdge(source, index, getEdgeValue(source, index));
            advanceIndex();
            return returnValue;
        }

        /** Remove is not implemented */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /** Advance the index to the next edge */
        private void advanceIndex() {
            do {
                index++;
            }
            while (index != getNumV() && 0 == getEdgeValue(source, index));
        }
    }
}

