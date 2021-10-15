package mnk.data.mysql.repositories

import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

import mnk.data.mysql.domain.Author
import mnk.data.mysql.domain.Book

@Repository
interface BookRepository : CrudRepository<Book, Long> {
    @Executable
    fun findAllByAuthor(author: Author): List<Book>
    @Executable
    fun findByTitle(title: String): Book?
}
