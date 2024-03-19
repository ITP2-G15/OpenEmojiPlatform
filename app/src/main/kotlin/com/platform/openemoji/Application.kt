import android.app.Application
import com.platform.openemoji.emoji.EmojiRepository
import com.platform.openemoji.events.EventsRepository
import com.platform.openemoji.news.NewsRepository

class Application : Application() {
    val emojiRepository by lazy { EmojiRepository(this) }
    val newsRepository by lazy { NewsRepository(this) }
    val eventsRepository by lazy { EventsRepository(this) }
}
