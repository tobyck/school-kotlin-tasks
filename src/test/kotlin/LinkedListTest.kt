import org.example.LinkedList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinkedListTest {
    // can't be bothered writing lots of proper tests
    @Test
    fun test() {
        LinkedList<Int>().run {
            assertEquals(true, isEmpty())

            append(5)
            append(2)

            assertEquals(5, at(0))
            assertEquals(2, at(1))
            assertEquals(2, length)

            insert(8, 1) // insert 8 at index 1

            assertEquals(5, at(0))
            assertEquals(8, at(1))

            append(6)

            assertEquals(6, at(3))
            assertEquals(4, length)

            assertEquals(2, remove(2))

            append(9)
            prepend(1)

            assertEquals(1, at(0))
            assertEquals(5, length)
            assertEquals(false, isEmpty())
        }
    }
}