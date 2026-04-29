package io.limao996.aliceswlib

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import io.limao996.aliceswlib.utils.UserAgentGenerator
import io.limao996.aliceswlib.utils.infoLog
import io.nightfish.lightnovelreader.api.book.BookRepositoryApi
import io.nightfish.lightnovelreader.api.book.LocalBookDataSourceApi
import io.nightfish.lightnovelreader.api.bookshelf.BookshelfRepositoryApi
import io.nightfish.lightnovelreader.api.plugin.LightNovelReaderPlugin
import io.nightfish.lightnovelreader.api.plugin.Plugin
import io.nightfish.lightnovelreader.api.text.TextProcessingRepositoryApi
import io.nightfish.lightnovelreader.api.userdata.UserDataDaoApi
import io.nightfish.lightnovelreader.api.userdata.UserDataRepositoryApi
import io.nightfish.lightnovelreader.api.web.WebBookDataSourceManagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jsoup.nodes.Entities.unescape


// 数据源主机地址
const val HOST = "https://www.alicesw.com"

@Suppress("unused")
@Plugin(
    version = BuildConfig.VERSION_CODE,
    name = "AliceswLib",
    versionName = BuildConfig.VERSION_NAME,
    author = "limao996",
    description = "数据源——爱丽丝书屋🔞",
    updateUrl = "https://v6.gh-proxy.com/https://github.com/dmzz-yyhyy/LightNovelReader-PluginRepository/blob/main/data/org.limao996.alicesw/",
    apiVersion = 2
)
class AliceswPlugin(
    val userDataDaoApi: UserDataDaoApi,
    val userDataRepositoryApi: UserDataRepositoryApi,
    val webBookDataSourceManagerApi: WebBookDataSourceManagerApi,
    val textProcessingRepositoryApi: TextProcessingRepositoryApi,
    val localBookDataSourceApi: LocalBookDataSourceApi,
    val bookRepositoryApi: BookRepositoryApi,
    val bookshelfRepositoryApi: BookshelfRepositoryApi,
) : LightNovelReaderPlugin {

    override fun onLoad() {
    }

    @Composable
    override fun PageContent(paddingValues: PaddingValues) {
        val content = LocalContext.current
    }
}

class PluginDiscoveryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {}
}
