package com.platform.openemoji.emoji

import android.content.res.Resources
import com.platform.openemoji.R
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

object EmojiMockData {
    // resources, the contents of the "res" folder, is not available outside of composables;
    // that's why it needs to be provided.
    // The experimental decoder is not worrisome since this mock data is temporary.
    @OptIn(ExperimentalSerializationApi::class)
    fun getFrom(resources: Resources): List<Emoji> {
        val emojiInputStream =
            resources.openRawResource(
                R.raw.emoji_mock_data,
            )
        return Json.decodeFromStream<List<Emoji>>(emojiInputStream)
    }
}
