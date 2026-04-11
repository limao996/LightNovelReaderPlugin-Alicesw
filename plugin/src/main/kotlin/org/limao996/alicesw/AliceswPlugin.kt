package org.limao996.alicesw

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.nightfish.lightnovelreader.api.book.BookRepositoryApi
import io.nightfish.lightnovelreader.api.book.LocalBookDataSourceApi
import io.nightfish.lightnovelreader.api.bookshelf.BookshelfRepositoryApi
import io.nightfish.lightnovelreader.api.plugin.LightNovelReaderPlugin
import io.nightfish.lightnovelreader.api.plugin.Plugin
import io.nightfish.lightnovelreader.api.text.TextProcessingRepositoryApi
import io.nightfish.lightnovelreader.api.ui.components.SettingsClickableEntry
import io.nightfish.lightnovelreader.api.ui.components.SettingsSwitchEntry
import io.nightfish.lightnovelreader.api.userdata.UserDataDaoApi
import io.nightfish.lightnovelreader.api.userdata.UserDataRepositoryApi
import io.nightfish.lightnovelreader.api.web.WebBookDataSourceManagerApi


// 数据源主机地址
const val HOST = "https://www.alicesw.com"

@Suppress("unused")
@Plugin(
    version = BuildConfig.VERSION_CODE,
    name = "Alicesw",
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .clip(RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            val checked by userDataRepositoryApi.booleanUserData("TestBooleanUserData")
                .getFlowWithDefault(true).collectAsState(true)
            SettingsSwitchEntry(
                modifier = Modifier.background(colorScheme.surfaceContainer),
                title = "测试选项",
                description = "Ciallo～(∠・ω< )⌒★",
                checked = checked,
                booleanUserData = userDataRepositoryApi.booleanUserData("TestBooleanUserData")
            )
            SettingsClickableEntry(
                modifier = Modifier.background(colorScheme.surfaceContainer),
                title = "测试点击",
                description = "0721",
                onClick = {
                    Toast.makeText(content, "带面纸了吗", Toast.LENGTH_LONG).show()
                })
        }
    }
}

class PluginDiscoveryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {}
}
