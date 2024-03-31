package com.platform.openemoji

import android.app.Application
import com.platform.openemoji.emoji.EmojiMockDataRepository
import com.platform.openemoji.emoji.EmojiRepository
import com.platform.openemoji.events.EventsMockDataRepository
import com.platform.openemoji.events.EventsRepository
import com.platform.openemoji.news.NewsMockDataRepository
import com.platform.openemoji.news.NewsRepository

interface RepositoryStore {
    val emojiRepository: EmojiRepository get() = object : EmojiRepository {}
    val newsRepository: NewsRepository get() = object : NewsRepository {}
    val eventsRepository: EventsRepository get() = object : EventsRepository {}
}

class Application : Application(), RepositoryStore {
    override val emojiRepository by lazy {
        EmojiMockDataRepository(this, simulatedDelay = 500)
    }
    override val newsRepository by lazy {
        NewsMockDataRepository(this, simulatedDelay = 500)
    }
    override val eventsRepository by lazy {
        EventsMockDataRepository(this, simulatedDelay = 500)
    }
}
