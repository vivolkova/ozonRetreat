package ru.technicalExcellence.codingDojo

/**
 * LeetCode 146. LRU Cache.
 *
 * Design a data structure that follows the constraints of a Least Recently Used
 * (LRU) cache. Both [get] and [put] run in O(1) average time by combining a
 * hash map with a doubly linked list.
 *
 * @param capacity maximum number of keys the cache can hold
 */
class LRUCache(private val capacity: Int) {

    private data class Node(val key: Int, var value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    private val cache = HashMap<Int, Node>(capacity)

    private val head = Node(0, 0)
    private val tail = Node(0, 0)

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val node = cache[key] ?: return -1
        removeNode(node)
        addToFront(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        cache[key]?.let {
            it.value = value
            removeNode(it)
            addToFront(it)
            return
        }

        if (cache.size == capacity) {
            val leastRecent = tail.prev!!
            removeNode(leastRecent)
            cache.remove(leastRecent.key)
        }

        val node = Node(key, value)
        cache[key] = node
        addToFront(node)
    }

    private fun addToFront(node: Node) {
        node.next = head.next
        node.prev = head
        head.next?.prev = node
        head.next = node
    }

    private fun removeNode(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }
}
