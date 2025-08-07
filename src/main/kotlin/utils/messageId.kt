package dev.naijun.talkapi.utils

/**
 * Generates a message ID based on timestamp and counter
 *
 * @param counter A counter value to add to the generated ID
 * @param timestamp Optional timestamp in milliseconds, uses current time if null
 * @return Generated message ID
 */
fun generateMessageId(counter: Int = 0, timestamp: Long? = null): Long {
    val actualTimestamp = timestamp ?: System.currentTimeMillis()

    val modValue = 2147483547L
    val roundedTime = ((actualTimestamp % modValue) / 100) * 100
    return roundedTime + counter
}