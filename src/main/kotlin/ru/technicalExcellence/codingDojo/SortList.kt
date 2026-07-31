package ru.technicalExcellence.codingDojo

/**
 * Definition for singly-linked list, matching the LeetCode signature.
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

/**
 * LeetCode 148. Sort List.
 *
 * Sorts a singly-linked list in ascending order in O(n log n) time and
 * O(log n) space using a top-down merge sort.
 */
class SortList {

    fun sortList(head: ListNode?): ListNode? {
        // 0 or 1 node is already sorted.
        if (head?.next == null) return head

        // Split the list into two halves.
        val middle = findMiddle(head)
        val right = middle.next
        middle.next = null

        val sortedLeft = sortList(head)
        val sortedRight = sortList(right)

        return merge(sortedLeft, sortedRight)
    }

    /**
     * Returns the node before the second half using the slow/fast pointer
     * technique, so the list can be cut into two balanced halves.
     */
    private fun findMiddle(head: ListNode): ListNode {
        var slow = head
        var fast = head.next
        while (fast?.next != null) {
            slow = slow.next!!
            fast = fast.next?.next
        }
        return slow
    }

    private fun merge(first: ListNode?, second: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var tail = dummy
        var a = first
        var b = second

        while (a != null && b != null) {
            if (a.`val` <= b.`val`) {
                tail.next = a
                a = a.next
            } else {
                tail.next = b
                b = b.next
            }
            tail = tail.next!!
        }

        tail.next = a ?: b
        return dummy.next
    }
}
