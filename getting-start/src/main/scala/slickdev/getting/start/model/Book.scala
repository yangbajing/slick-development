package slickdev.getting.start.model

import java.time.OffsetDateTime

case class Book(isbn: String, title: String, author: String, description: String, createdAt: OffsetDateTime)
