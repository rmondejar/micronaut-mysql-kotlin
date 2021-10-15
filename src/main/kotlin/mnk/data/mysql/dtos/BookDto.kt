package mnk.data.mysql.dtos

import java.time.Instant

import io.micronaut.core.annotation.Introspected

@Introspected
data class BookDto(val title: String?, val pubDate: Instant?, val author: String?) {
    constructor(): this(null, null, null)
}