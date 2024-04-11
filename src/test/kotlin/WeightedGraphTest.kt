import kotlin.test.Test
import kotlin.test.assertEquals

class WeightedGraphTest {
    @Test
    fun testGraph() {
        WeightedGraph<String>().run {
            val a = addVertex("A")
            addVertices("B", "C", "D")

            addEdge(WeightedGraph.EdgeType.DIRECTED, "B", "A", 3.0)
            addEdge(WeightedGraph.EdgeType.UNDIRECTED, "B", "C", 2.0)
            addEdge(WeightedGraph.EdgeType.DIRECTED, "A", "C", 7.0)
            addEdge(WeightedGraph.EdgeType.UNDIRECTED, "A", "D", 1.5)
            addEdge(WeightedGraph.EdgeType.UNDIRECTED, "D", "B", 2.5)

            assertEquals(
                setOf(
                    WeightedGraph.Edge("A", 3.0),
                    WeightedGraph.Edge("C", 2.0),
                    WeightedGraph.Edge("D", 2.5)
                ),
                neighbours("B")
            )

            assertEquals(
                setOf(
                    WeightedGraph.Edge("C", 7.0),
                    WeightedGraph.Edge("D", 1.5)
                ),
                neighbours(a)
            )
        }
    }
}