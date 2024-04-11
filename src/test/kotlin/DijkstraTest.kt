import kotlin.test.Test
import kotlin.test.assertEquals

class DijkstraTest {
    @Test
    fun test() {
        val graph = WeightedGraph<String>()

        val a = graph.addVertex("A")
        val b = graph.addVertex("B")
        val c = graph.addVertex("C")
        val d = graph.addVertex("D")

        graph.addEdge(WeightedGraph.EdgeType.DIRECTED, "B", "A", 3.0)
        graph.addEdge(WeightedGraph.EdgeType.UNDIRECTED, "B", "C", 2.0)
        graph.addEdge(WeightedGraph.EdgeType.DIRECTED, "A", "C", 7.0)
        graph.addEdge(WeightedGraph.EdgeType.UNDIRECTED, "A", "D", 1.5)
        graph.addEdge(WeightedGraph.EdgeType.UNDIRECTED, "D", "B", 2.5)

        val result = dijkstra(graph, "A", "C")

        assert(result != null)

        assertEquals(6.0, result!!.distance)
        assertEquals("[A, D, B, C]", result.verticesAsList().toString())
    }
}