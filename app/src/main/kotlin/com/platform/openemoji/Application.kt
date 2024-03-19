import android.app.Application
import com.platform.openemoji.emoji.EmojiRepository
import com.platform.openemoji.news.NewsRepository

class EmojiApplication : Application() {
    val emojiRepository by lazy { EmojiRepository(this) }
    val newsRepository by lazy { NewsRepository(this) }
}
