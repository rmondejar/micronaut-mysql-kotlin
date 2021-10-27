package mnk.data.mysql.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import mnk.data.mysql.domain.Author
import mnk.data.mysql.dtos.AuthorDto
import mnk.data.mysql.mappers.AuthorMapper
import mnk.data.mysql.repositories.AuthorRepository

@Singleton
class AuthorService {
    @Inject
    lateinit var authorsRepository: AuthorRepository
    @Inject
    lateinit var authorMapper: AuthorMapper

    fun findAll(): List<AuthorDto> {
        val authorDtos: MutableList<AuthorDto> = ArrayList()
        authorsRepository.findAll().forEach { author -> authorDtos.add(authorMapper.toDto(author)) }
        return authorDtos
    }

    fun findAuthor(authorName: String): AuthorDto? {
        return authorsRepository.findByName(authorName)?.let { author -> authorMapper.toDto(author) }
    }

    fun createAuthor(authorDto: AuthorDto): AuthorDto {
        val author: Author = authorsRepository.save(authorMapper.toEntity(authorDto))
        return authorMapper.toDto(author)
    }
}
