package com.platform.openemoji

import android.app.Application
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.amplitude.android.DefaultTrackingOptions
import com.amplitude.core.ServerZone
import com.platform.openemoji.emoji.EmojiMockDataRepository
import com.platform.openemoji.emoji.EmojiRepository
import com.platform.openemoji.events.EventsMockDataRepository
import com.platform.openemoji.events.EventsRepository
import com.platform.openemoji.favorites.FavoritesDataRepository
import com.platform.openemoji.favorites.FavoritesRepository
import com.platform.openemoji.game.GameMockDataRepository
import com.platform.openemoji.game.GameRepository
import com.platform.openemoji.news.NewsMockDataRepository
import com.platform.openemoji.news.NewsRepository

interface RepositoryStore {
    val emojiRepository: EmojiRepository get() = object : EmojiRepository {}
    val newsRepository: NewsRepository get() = object : NewsRepository {}
    val eventsRepository: EventsRepository get() = object : EventsRepository {}
    val favoritesRepository: FavoritesRepository get() = object : FavoritesRepository {}
    val gameRepository: GameRepository get() = object : GameRepository {}
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
    override val favoritesRepository by lazy {
        FavoritesDataRepository(this)
    }
    override val gameRepository by lazy {
        GameMockDataRepository(this, simulatedDelay = 500)
    }

    companion object {
        lateinit var amplitude: Amplitude
    }

    override fun onCreate() {
        super.onCreate()
        amplitude =
            Amplitude(
                Configuration(
                    // It's safe to expose api key
                    // https://github.com/amplitude/Amplitude-Javascript/issues/100
                    apiKey = "efe7c6f94fd4d20f32b6f9b91f66d6be",
                    context = applicationContext,
                    defaultTracking = DefaultTrackingOptions.ALL,
                    serverZone = ServerZone.EU,
                ),
            )
    }
}
