package com.ryosuke_mita.anatadare

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

const val INCOMING_PHONE_NUMBER = "incoming_phone_number";
class WebViewActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val number = intent.getStringExtra(INCOMING_PHONE_NUMBER)
        setContent { WebViewWidget(url = "https://google.com/search?q=$number") }
    }
}

@Composable
fun WebViewWidget(url: String) {
    AndroidView(
        factory = { WebView(it) },
        update = {webView ->
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
    })
}