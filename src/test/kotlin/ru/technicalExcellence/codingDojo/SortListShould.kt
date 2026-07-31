package ru.technicalExcellence.codingDojo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SortListShould {

    private val sortList = SortList()

    @Test
    fun returnNullForEmptyList() {
        assertNull(sortList.sortList(toList()))
    }

    @Test
    fun returnSingleElementListUnchanged() {
        assertEquals(listOf(42), toKotlinList(sortList.sortList(toList(42))))
    }

    @ParameterizedTest
    @MethodSource("lists")
    fun sortListInAscendingOrder(input: List<Int>, expected: List<Int>) {
        val actual = toKotlinList(sortList.sortList(toList(*input.toIntArray())))

        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun lists(): Stream<Arguments> = Stream.of(
            // LeetCode example 1
            Arguments.of(listOf(4, 2, 1, 3), listOf(1, 2, 3, 4)),
            // LeetCode example 2
            Arguments.of(listOf(-1, 5, 3, 4, 0), listOf(-1, 0, 3, 4, 5)),
            // Already sorted
            Arguments.of(listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5)),
            // Reverse sorted
            Arguments.of(listOf(5, 4, 3, 2, 1), listOf(1, 2, 3, 4, 5)),
            // Duplicates
            Arguments.of(listOf(3, 1, 2, 3, 1), listOf(1, 1, 2, 3, 3)),
            // All equal
            Arguments.of(listOf(7, 7, 7), listOf(7, 7, 7)),
            // Two elements
            Arguments.of(listOf(2, 1), listOf(1, 2)),
            // Negatives and boundary values
            Arguments.of(
                listOf(0, -100000, 100000, -1, 1),
                listOf(-100000, -1, 0, 1, 100000)
            )
        )
    }

    /** Builds a linked list from the given values. */
    private fun toList(vararg values: Int): ListNode? {
        val dummy = ListNode(0)
        var tail = dummy
        for (value in values) {
            tail.next = ListNode(value)
            tail = tail.next!!
        }
        return dummy.next
    }

    /** Converts a linked list back into a Kotlin list for assertions. */
    private fun toKotlinList(head: ListNode?): List<Int> {
        val result = mutableListOf<Int>()
        var node = head
        while (node != null) {
            result.add(node.`val`)
            node = node.next
        }
        return result
    }
}
