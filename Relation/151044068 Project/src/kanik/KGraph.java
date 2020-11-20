package kanik;

import java.util.Iterator;

public interface KGraph {

    // Accessor Methods
    /** Return the number of vertices.
     @return The number of vertices
     */
    int getNumV();

    /** Determine whether this is a directed graph.
     @return True if this is a directed graph
     */
    boolean isDirected();

    /** Insert a new edge into the graph.
     @param edge The new edge
     */
    void insert(KEdge edge);

    /** Determine whether an edge exists.
     @param source - The source vertex
     @param dest - The destination vertex
     @return True if there is an edge from source to dest
     */
    boolean isEdge(int source, int dest);

    /** Get the edge between two vertices.
     @param source - The source vertex
     @param dest - The destination vertex
     @return The KEdge between these two vertices or an KEdge with a weight of 0 if there is no edge
     */
    KEdge getEdge(int source, int dest);

    /** Return an iterator to the edges connected to a given vertex.
     * @param source - The source vertex
     * @return An Iterator to the vertices connected to source
     */
    Iterator<KEdge> edgeIterator(int source);
}
