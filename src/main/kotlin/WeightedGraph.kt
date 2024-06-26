// not optimal and won't work for all use cases but it'll do the job for me

class WeightedGraph<T> { // T is the type of the data representing a vertex
    enum class EdgeType { DIRECTED, UNDIRECTED }
    data class Edge<T>(val destination: T, val weight: Double)

    private val adjacencyLists = hashMapOf<T, MutableSet<Edge<T>>>()

    fun addVertex(data: T): T {
        adjacencyLists[data] = mutableSetOf()
        return data
    }

    fun addVertices(vararg data: T) = data.forEach { addVertex(it) }

    fun addEdge(type: EdgeType, source: T, destination: T, weight: Double) {
        // make sure that both vertices are in the graph
        if (!adjacencyLists.containsKey(source) || !adjacencyLists.containsKey(destination)) {
            throw IllegalArgumentException("Both vertices must be in the graph!")
        }

        adjacencyLists[source]!!.add(Edge(destination, weight))

        // if the edge goes both ways, add another one in the other direction
        if (type == EdgeType.UNDIRECTED) adjacencyLists[destination]!!.add(Edge(source, weight))
    }

    fun neighbours(vertex: T): Set<Edge<T>> =
        adjacencyLists[vertex] ?: throw IllegalArgumentException("Vertex $vertex not in graph!")

    fun vertices(): Set<T> = adjacencyLists.keys
}