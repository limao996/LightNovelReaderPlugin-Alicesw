package org.limao996.alicesw.utils

import cxhttp.CxHttp
import cxhttp.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

private val requestLimiter = Semaphore(3)

suspend fun get(url: String, useWindowsUA: Boolean = false): Document? =
    withContext(Dispatchers.IO) {
        requestLimiter.withPermit {
            suspend fun get(): Response {
                return CxHttp.get(url) {
                    header(
                        "user-agent", UserAgentGenerator().generate(
                            if (useWindowsUA) UserAgentGenerator.Platform.Windows
                            else UserAgentGenerator.Platform.Android
                        )
                    )
                }.scope(this).await()
            }

            var retryTime = 3
            var retryDelay = 2500L
            var response = get()
            while (!response.isSuccessful && retryTime >= 1) {
                response = get()
                retryTime--
                delay(retryDelay)
                retryDelay *= 2
            }
            val doc = response.body?.string()?.let(Jsoup::parse)
            return@withContext doc
        }
    }

