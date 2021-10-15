package mnk.data.mysql.dtos

import io.micronaut.core.annotation.Introspected

@Introspected
data class AuthorDto(val name: String?, val birthYear: Int?) {
    constructor(): this(null, null)
}
