package com.platform.openemoji

import android.app.Application
import com.platform.openemoji.emoji.EmojiMockDataRepository
import com.platform.openemoji.events.EventsMockDataRepository
import com.platform.openemoji.news.NewsMockDataRepository

class Application : Application() {
    val emojiRepository by lazy {
        EmojiMockDataRepository(this, simulatedDelay = 500)
    }
    val newsRepository by lazy {
        NewsMockDataRepository(this, simulatedDelay = 500)
    }
    val eventsRepository by lazy {
        EventsMockDataRepository(this, simulatedDelay = 500)
    }
}
