data class Path<T>(
    val previous: Path<T>?, // the path that led to this vertex
    val current: T, // the vertex that this path has reached
    val distance: Double // total distance of the path
) : Comparable<Path<T>> {
    // this class must be comparable with itself because it will be used in a priority queue
    override fun compareTo(other: Path<T>): Int = distance.compareTo(other.distance)

    // backtracks the path through all the parent paths and returns the vertices in a list
    fun verticesAsList(): LinkedList<T> {
        val list = LinkedList<T>() // storing in a linked for O(1) prepend
        var path: Path<T>? = this
        while (path != null) { // while there are still parent paths
            list.prepend(path.current)
            path = path.previous
        }
        return list
    }
}

fun <T : Comparable<T>> dijkstra(graph: WeightedGraph<T>, start: T, end: T): Path<T>? {
    // a hash map mapping every vertex to infinity except the start vertex to 0
    val distances = hashMapOf(*graph.vertices().map {
        it to if (it == start) 0.0 else Double.POSITIVE_INFINITY
    }.toTypedArray())

    val queue = PairingHeap(Path(null, start, 0.0))

    while (!queue.isEmpty()) { // while there are still vertices to visit
        val path = queue.deleteMin()!! // take off the shortest path from the queue
        if (path.current == end) return path

        graph.neighbours(path.current).forEach {
            val newDistance = distances[path.current]!! + it.weight // find the new distance to the neighbour
            if (newDistance < distances[it.destination]!!) { // if it's less than the shortest way to the neighbour
                distances[it.destination] = newDistance // update it
                queue.insert(Path(path, it.destination, newDistance)) // and insert it into the queue
            }
        }
    }

    return null
}