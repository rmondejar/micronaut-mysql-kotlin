package mnk.data.mysql.controllers

import javax.validation.Valid
import jakarta.inject.Inject

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

import mnk.data.mysql.dtos.AuthorDto
import mnk.data.mysql.services.AuthorService

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
        var authorDto : AuthorDto? = authorService.findAuthor(name)
        return if (authorDto != null) {
            HttpResponse.ok(authorDto)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post
    fun createAuthor(@Body authorDto: @Valid AuthorDto): HttpResponse<AuthorDto> {
        var existingAuthor : AuthorDto? = authorService.findAuthor(authorDto.name!!)
        return if (existingAuthor != null) {
            HttpResponse.badRequest()
        } else {
            HttpResponse.created(authorService.createAuthor(authorDto))
        }
    }
}
