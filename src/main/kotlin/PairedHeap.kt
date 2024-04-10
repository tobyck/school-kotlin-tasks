package org.example

class PairingHeap<T : Comparable<T>>(
    private var data: T? = null,
    private var children: LinkedList<PairingHeap<T>> = LinkedList()
) {
    fun getMin() = data

    // inherit the properties of a different heap
    private fun become(other: PairingHeap<T>) {
        data = other.data
        children = other.children
    }

    // merges another heap into this one
    private fun meld(other: PairingHeap<T>) {
        if (other.data == null) return // if the other heap is empty then there's nothing to do

        if (data == null) become(other)
        else {
            if (data!! > other.data!!) data = other.data // take the smaller of the two roots
            children.append(other) // add the other heap the list of subheaps in this heap
        }
    }

    // insert by melding a new heap with a single element
    fun insert(data: T): Unit = meld(PairingHeap(data))

    fun deleteMin(): T? {
        if (data == null) throw NoSuchElementException("Cannot delete from an empty heap!")

        return data.also {
            when (children.length) {
                0 -> data = null
                1 -> become(children.at(0))
                else -> become(children.reduce { acc, heap -> acc.apply { meld(heap) } }!!) // reduce by meld
            }
        }
    }
}