package mnk.data.mysql.controllers

import javax.validation.Valid
import jakarta.inject.Inject

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

import mnk.data.mysql.dtos.AuthorDto
import mnk.data.mysql.dtos.BookDto
import mnk.data.mysql.services.AuthorService
import mnk.data.mysql.services.BookService

@Controller("/books")
class BookController {
    @Inject
    lateinit var bookService: BookService
    @Inject
    lateinit var authorService: AuthorService

    @Get("/all")
    fun allBooks(): List<BookDto> {
        return bookService.findAll()
    }

    @Get
    fun getBooksByAuthor(@QueryValue author: String): HttpResponse<List<BookDto>> {
        val authorDto : AuthorDto?  = authorService.findAuthor(author)
        return authorDto?.let {
            HttpResponse.ok(
                bookService.findAllByAuthorName(authorDto.name!!)
            )
        } ?: HttpResponse.notFound()
    }

    @Post
    fun postBook(@Body bookDto: @Valid BookDto): HttpResponse<BookDto> {
        return if (
            authorService.findAuthor(bookDto.author!!) == null ||
            bookService.findByTitle(bookDto.title!!) != null) {
                HttpResponse.badRequest()
        } else
            bookService.createBook(bookDto)
                .let { book -> HttpResponse.created(book) }
                ?: HttpResponse.badRequest()
    }
}
