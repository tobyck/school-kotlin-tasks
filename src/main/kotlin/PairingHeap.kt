class PairingHeap<T : Comparable<T>>(
    private var data: T? = null,
    private var subheaps: LinkedList<PairingHeap<T>> = LinkedList()
) {
    fun getMin() = data

    fun isEmpty() = data == null

    // inherit the properties of a different heap
    private fun become(other: PairingHeap<T>) {
        data = other.data
        subheaps = other.subheaps
    }

    // merges another heap into this one
    private fun meld(other: PairingHeap<T>) {
        if (other.data == null) return // if the other heap is empty then there's nothing to do

        if (data == null) become(other)
        else {
            if (data!! < other.data!!) { // if the root of this heap is still less than the other one
                subheaps.prepend(other)
            } else { // otherwise take the root from the other heap, and set the subheaps to this heap and the subheaps of the other heap
                subheaps = other.subheaps.apply { prepend(PairingHeap(data, subheaps)) }
                data = other.data
            }
        }
    }

    // insert by melding a new heap with a single element
    fun insert(data: T): Unit = meld(PairingHeap(data))

    fun deleteMin(): T? {
        if (data == null) throw NoSuchElementException("Cannot delete from an empty heap!")

        return data.also {
            when (subheaps.length) {
                0 -> data = null
                1 -> become(subheaps.at(0))
                else -> become(subheaps.reduce { acc, heap -> acc.apply { meld(heap) } }!!) // merge all the subheaps into one
            }
        }
    }
}