package mnk.data.mysql.domain

import javax.persistence.*

@Entity
data class Author(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id: Long?,
                val name: String,
                val birthYear: Int) {
    constructor(name: String, birthYear: Int): this(null, name, birthYear)
}
