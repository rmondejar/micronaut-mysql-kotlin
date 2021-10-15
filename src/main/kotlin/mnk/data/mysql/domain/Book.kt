package mnk.data.mysql.domain

import java.time.Instant
import javax.persistence.*

@Entity
data class Book(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id: Long?,
                val title: String,
                val pubDate: Instant,
                @ManyToOne
                @JoinColumn(name = "AUTHOR")
                val author: Author) {
    constructor(title: String, pubDate: Instant, author: Author):
            this(null, title, pubDate, author)
}
