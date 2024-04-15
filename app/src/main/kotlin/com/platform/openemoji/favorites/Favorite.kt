package com.platform.openemoji.favorites

import kotlinx.serialization.Serializable

@Serializable
data class Favorite(
    val name: String,
    val emojiCodes: Array<String>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Favorite

        if (name != other.name) return false
        if (!emojiCodes.contentEquals(other.emojiCodes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + emojiCodes.contentHashCode()
        return result
    }
}
