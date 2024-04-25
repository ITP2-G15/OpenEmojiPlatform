package com.platform.openemoji.ads

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun InlineAd() {
    Box(
        modifier = Modifier.fillMaxWidth().height(100.dp),
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.LARGE_BANNER)
                    adUnitId = "ca-app-pub-3940256099942544/9214589741"
                    loadAd(AdRequest.Builder().build())
                }
            },
        )
    }
}
