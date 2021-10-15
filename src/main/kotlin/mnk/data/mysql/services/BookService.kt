package mnk.data.mysql.services

import java.util.*
import jakarta.inject.Inject
import jakarta.inject.Singleton

import mnk.data.mysql.dtos.BookDto
import mnk.data.mysql.mappers.BookMapper
import mnk.data.mysql.repositories.AuthorRepository
import mnk.data.mysql.repositories.BookRepository

@Singleton
class BookService {
    @Inject
    lateinit var bookRepository: BookRepository
    @Inject
    lateinit var bookMapper: BookMapper
    @Inject
    lateinit var authorRepository: AuthorRepository

    fun findAll(): List<BookDto> {
        return bookRepository.findAll().map { book -> bookMapper.toDto(book) }
    }

    fun findByTitle(title: String): BookDto? {
        return bookRepository.findByTitle(title)?.let{ book -> bookMapper.toDto(book) }
    }

    fun findAllByAuthorName(authorName: String): List<BookDto> {
        val bookDtos: MutableList<BookDto> = ArrayList()
        authorRepository.findByName(authorName)?.let { author ->
            bookRepository.findAllByAuthor(author).forEach { book ->
                bookDtos.add(
                    bookMapper.toDto(book)
                )
            }
        }
        return bookDtos
    }

    fun createBook(bookDto: BookDto): BookDto {
        return authorRepository.findByName(bookDto.author!!)
                .let { author ->
                    bookMapper.toDto(
                        bookRepository.save(bookMapper.toEntity(bookDto, author!!))
                    )
                }

    }
}
