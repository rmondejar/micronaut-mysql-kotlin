package mnk.data.mysql.mappers

import jakarta.inject.Inject
import jakarta.inject.Singleton
import mnk.data.mysql.domain.Author
import mnk.data.mysql.domain.Book
import mnk.data.mysql.dtos.AuthorDto
import mnk.data.mysql.dtos.BookDto

@Singleton
class BookMapper {

    @Inject
    lateinit var authorMapper: AuthorMapper

    fun toDto(book: Book): BookDto {
        val authorDto: AuthorDto = authorMapper.toDto(book.author)
        return BookDto(book.title, book.pubDate, authorDto.name)
    }

    fun toEntity(bookDto: BookDto, author: Author): Book {
        return Book(bookDto.title!!, bookDto.pubDate!!, author)
    }
}
