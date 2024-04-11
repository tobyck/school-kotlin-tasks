// (The introductory comments were the beginning of an attempt to teach my teacher Kotlin but I was unsuccessful.)

// generics work just like in most other languages
class LinkedList<T>(vararg elements: T) { // varargs is like ... in Java and JS
    // classes with implicit constructors:
    class Node<T>(val data: T, var next: Node<T>? = null) // the ? makes a type nullable

    private var head: Node<T>? = null // vars are mutable, vals are not
    private var tail: Node<T>? = null // we don't need semicolons!

    var length: Int = 0
        private set // here we're overriding the setter of the var so that you can only set the length inside the class

    // this is one of a few ways to do constructors
    init {
        /* When a lambda (enclosed in curly braces) is the last argument to a function, it can be moved outside the
         * parentheses; if it's the only argument, the parentheses can be omitted. Also, if you don't specify any
         * arguments for a lambda, it's implicitly a monadic lambda with an argument called "it".
         */
        elements.forEach { append(it) }
    }

    // kotlin is "fun"!
    fun append(data: T) {
        if (head == null) {
            // set the head and tail to the first and only node
            head = Node(data)
            tail = head
        } else {
            // add new node then set the tail to said node
            tail?.next = Node(data)
            tail = tail?.next
        }

        length++
    }

    fun prepend(data: T) {
        head = Node(data, head) // makes a new head which points to the old one
        length++
    }

    // one line functions can be written like this
    fun isEmpty() = head == null

    private fun nodeAt(index: Int): Node<T> {
        if (index < 0 || index >= length) throw IndexOutOfBoundsException("Index $index is out of bounds!")

        /* A `when` expression is basically a more powerful switch statement which can also be used as an expression
         * (as can if statements and try-catch blocks).
         *
         * All the !!s in here are to tell the compiler the value is definitely not null (because of the check above).
         * There's probably a better and more idiomatic way to do this, but I've only started learning Kotlin recently.
         */
        return when (index) {
            0 -> head!!
            length - 1 -> tail!!
            else -> {
                var node = head!! // start at the head
                var i = 0
                while (i != index) { // go to the next node and increment i until we reach the desired index
                    node = node.next!!
                    i++
                }
                node // this will be the result of the expression and therefore this function
            }
        }
    }

    fun at(index: Int) = this.nodeAt(index).data

    fun insert(data: T, index: Int) {
        when {
            index < 0 -> throw IndexOutOfBoundsException("Cannot insert at negative index!")
            index > length -> throw IndexOutOfBoundsException("Index $index is out of bounds!")
            index == 0 -> prepend(data)
            index == length -> append(data)
            else -> {
                // set the previous node's next node to a new node pointing to the rest of the list
                val previous = this.nodeAt(index - 1)
                previous.next = Node(data, previous.next)
                length++
            }
        }
    }

    fun remove(index: Int): T? {
        if (length == 0) throw IndexOutOfBoundsException("Cannot remove from an empty list!")

        when (index) { // here we're using it more like a traditional switch statement
            0 -> { // set the head to the node after the head and return the original one
                val result = head?.data
                head = head?.next
                length--
                return result
            }
            in 1..<length -> { // 1..<length is a range from 1 to length - 1
                /* This will set the node before the one to remove to the one after the one to remove, skipping the one
                 * that we don't want any more, thus deleting it. But if we're deleting the tail, we can just set the
                 * second to last node (which is what would be stored in the `previous` variable) to null.
                 */
                val previous = this.nodeAt(index - 1)
                val toRemove = previous.next // store this to return afterward
                previous.next = if (index == length - 1) null else previous.next?.next

                if (index == length - 1) tail = previous

                length--

                return toRemove?.data
            }
            else -> throw IndexOutOfBoundsException("Index $index is out of bounds!")
        }
    }

    fun reduce(f: (T, T) -> T): T? {
        if (isEmpty()) return null
        var accumulator = head!!.data // start the accumulator with the first element
        var current = head!!.next // start the current node at the second element
        while (current != null) { // for each of the rest elements, apply the function to the accumulator and the element
            accumulator = f(accumulator, current.data)
            current = current.next
        }
        return accumulator
    }

    override fun toString(): String = buildString { // we're now in the context of a StringBuilder
        append("[")
        var current = head
        while (current != null) {
            append(current.data)
            current = current.next
            if (current != null) append(", ")
        }
        append("]")
    }
}