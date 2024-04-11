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
        val e = graph.addVertex("E")

        graph.addEdge(WeightedGraph.EdgeType.DIRECTED, b, a, 3.0)
        graph.addEdge(WeightedGraph.EdgeType.UNDIRECTED, b, c, 2.0)
        graph.addEdge(WeightedGraph.EdgeType.DIRECTED, a, e, 1.0)
        graph.addEdge(WeightedGraph.EdgeType.DIRECTED, e, c, 6.0)
        graph.addEdge(WeightedGraph.EdgeType.UNDIRECTED, a, d, 1.5)
        graph.addEdge(WeightedGraph.EdgeType.UNDIRECTED, d, b, 2.5)

        val result = dijkstra(graph, a, c)

        assert(result != null)

        assertEquals(6.0, result!!.distance)
        assertEquals("[A, D, B, C]", result.verticesAsList().toString())
    }
}