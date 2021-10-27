package mnk.data.mysql.repositories

import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import mnk.data.mysql.domain.Author

@Repository
interface AuthorRepository : CrudRepository<Author, Long> {
    @Executable
    fun findByName(name: String): Author?
}
