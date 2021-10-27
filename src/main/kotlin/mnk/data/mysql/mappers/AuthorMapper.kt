package mnk.data.mysql.mappers

import jakarta.inject.Singleton
import mnk.data.mysql.domain.Author
import mnk.data.mysql.dtos.AuthorDto

@Singleton
class AuthorMapper {
    fun toEntity(authorDto: AuthorDto): Author {
        return Author(authorDto.name!!, authorDto.birthYear!!)
    }

    fun toDto(author: Author): AuthorDto {
        return AuthorDto(author.name, author.birthYear)
    }
}
