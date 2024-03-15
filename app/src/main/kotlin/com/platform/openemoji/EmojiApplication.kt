import android.app.Application
import com.platform.openemoji.emoji.EmojiRepository

class EmojiApplication : Application() {
    val emojiRepository by lazy { EmojiRepository(this) }
}
