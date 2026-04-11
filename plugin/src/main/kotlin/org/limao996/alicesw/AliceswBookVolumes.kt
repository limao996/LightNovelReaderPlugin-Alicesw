package org.limao996.alicesw

import io.nightfish.lightnovelreader.api.book.BookVolumes
import io.nightfish.lightnovelreader.api.book.ChapterInformation
import io.nightfish.lightnovelreader.api.book.Volume
import org.limao996.alicesw.utils.get

suspend fun AliceswBookVolumes(id: String): BookVolumes {
    val soup = get("$HOST/other/chapters/id/$id.html")

    val items = soup?.selectFirst(".section-list")?.children()
    val chapters = items?.map {
        val doc = it.child(0)
        val id = Regex("/book/[^/]+/([^/]+)\\.html").find(
            doc?.attr("href") ?: ""
        )?.groupValues?.get(1) ?: ""
        ChapterInformation(
            id = id,
            title = doc.text(),
        )
    }

    val volumeId = Regex("/book/([^/]+)/[^/]+\\.html").find(
        items?.first()?.child(0)?.attr("href") ?: ""
    )?.groupValues?.get(1) ?: ""

    return BookVolumes(
        id, listOf(
            Volume(
                volumeId = volumeId, volumeTitle = "正文", chapters = chapters ?: emptyList()
            )
        )
    )
}