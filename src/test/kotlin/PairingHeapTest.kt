import kotlin.test.Test
import kotlin.test.assertEquals

class PairingHeapTest {
    data class ExampleObject(val value: Int) : Comparable<ExampleObject> {
        override fun compareTo(other: ExampleObject): Int {
            return value.compareTo(other.value)
        }
    }

    @Test
    fun test() {
        PairingHeap<ExampleObject>().run {
            insert(ExampleObject(3))
            insert(ExampleObject(6))
            insert(ExampleObject(8))
            insert(ExampleObject(2))
            insert(ExampleObject(0))
            assertEquals(ExampleObject(0), getMin())
            assertEquals(ExampleObject(0), deleteMin())
            assertEquals(ExampleObject(2), deleteMin())
            assertEquals(ExampleObject(3), deleteMin())
            assertEquals(ExampleObject(6), deleteMin())
            assertEquals(ExampleObject(8), deleteMin())
        }
    }
}