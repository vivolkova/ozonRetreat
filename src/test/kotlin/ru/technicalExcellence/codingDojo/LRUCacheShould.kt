package ru.technicalExcellence.codingDojo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LRUCacheShould {

    @Test
    fun supportLeetCodeExampleOne() {
        val cache = LRUCache(2)

        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))

        cache.put(3, 3)
        assertEquals(-1, cache.get(2))

        cache.put(4, 4)
        assertEquals(-1, cache.get(1))
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }

    @Test
    fun returnMinusOneForMissingKey() {
        val cache = LRUCache(1)

        assertEquals(-1, cache.get(0))
    }

    @Test
    fun updateValueWithoutChangingPositionOnGet() {
        val cache = LRUCache(2)

        cache.put(1, 1)
        cache.put(2, 2)
        cache.get(1)
        cache.put(3, 3)

        // 1 was used most recently, so 2 was evicted instead.
        assertEquals(1, cache.get(1))
        assertEquals(-1, cache.get(2))
        assertEquals(3, cache.get(3))
    }

    @Test
    fun updateValueOnPutOfExistingKey() {
        val cache = LRUCache(2)

        cache.put(1, 1)
        cache.put(1, 2)

        assertEquals(2, cache.get(1))
    }

    @Test
    fun refreshRecencyWhenExistingKeyIsUpdated() {
        val cache = LRUCache(2)

        cache.put(1, 1)
        cache.put(2, 2)
        cache.put(1, 10)
        cache.put(3, 3)

        assertEquals(10, cache.get(1))
        assertEquals(-1, cache.get(2))
        assertEquals(3, cache.get(3))
    }

    @Test
    fun handleSingleCapacityCache() {
        val cache = LRUCache(1)

        cache.put(1, 1)
        cache.put(2, 2)

        assertEquals(-1, cache.get(1))
        assertEquals(2, cache.get(2))
    }

    @Test
    fun surviveGetOnEvictedKey() {
        val cache = LRUCache(1)

        cache.put(1, 1)
        cache.get(1)
        cache.put(2, 2)

        assertEquals(-1, cache.get(1))
    }

    @Test
    fun supportRepeatedPutAndGetCycles() {
        val cache = LRUCache(3)

        for (i in 1..5) {
            cache.put(i, i * 10)
        }

        // Keys 3, 4 and 5 are in the cache, keys 1 and 2 were evicted.
        assertEquals(-1, cache.get(1))
        assertEquals(-1, cache.get(2))
        assertEquals(30, cache.get(3))
        assertEquals(40, cache.get(4))
        assertEquals(50, cache.get(5))
    }
}
