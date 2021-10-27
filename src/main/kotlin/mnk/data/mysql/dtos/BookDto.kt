package mnk.data.mysql.dtos

import io.micronaut.core.annotation.Introspected
import java.time.Instant

@Introspected
data class BookDto(val title: String?, val pubDate: Instant?, val author: String?) {
    constructor() : this(null, null, null)
}
