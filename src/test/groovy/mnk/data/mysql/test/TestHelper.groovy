package mnk.data.mysql.test

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import mnk.data.mysql.domain.Author
import mnk.data.mysql.domain.Book
import mnk.data.mysql.dtos.AuthorDto
import mnk.data.mysql.dtos.BookDto

import java.time.Instant

/**
 * Creates plain objects without database generated fields
 */
class TestHelper {

    static Author createAuthor(String name = "no name", Integer birthYear = 1982) {
        return new Author(null, name, birthYear)
    }

    static AuthorDto createAuthorDto(String name = "no name", Integer birthYear = 1982) {
        return new AuthorDto(name, birthYear)
    }
    
    static Book createBook(String title = "no title", Author author = createAuthor(), Instant pubDate = Instant.now()) {
        return new Book(null, title, pubDate, author)
    }

    static BookDto createBookDto(String title = "no title", String author = "no name", Instant pubDate = Instant.now()) {
        return new BookDto(title, pubDate, author)
    }

    static <O> HttpResponse<O> requestWithoutException(HttpClient client, HttpRequest req, Argument<O> returnedType) {
        HttpResponse<O> response

        try {
            response = client.toBlocking().exchange(req, returnedType)
        } catch (HttpClientResponseException e) {
            response = e.response
        }

        return response
    }
}
