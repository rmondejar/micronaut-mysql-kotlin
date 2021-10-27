package mnk.data.mysql.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject
import mnk.data.mysql.dtos.AuthorDto
import mnk.data.mysql.services.AuthorService
import javax.validation.Valid

@Controller("/authors")
class AuthorController {
    @Inject
    lateinit var authorService: AuthorService

    @Get("/all")
    fun allAuthors(): List<AuthorDto> {
        return authorService.findAll()
    }

    @Get
    fun getAuthor(@QueryValue name: String): HttpResponse<AuthorDto> {
        var authorDto: AuthorDto? = authorService.findAuthor(name)
        return if (authorDto != null) {
            HttpResponse.ok(authorDto)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post
    fun createAuthor(@Body authorDto: @Valid AuthorDto): HttpResponse<AuthorDto> {
        var existingAuthor: AuthorDto? = authorService.findAuthor(authorDto.name!!)
        return if (existingAuthor != null) {
            HttpResponse.badRequest()
        } else {
            HttpResponse.created(authorService.createAuthor(authorDto))
        }
    }
}
