package slickdev.getting.start.repository

import java.time.{Instant, OffsetDateTime}

import slickdev.getting.start.model.{Author, Book}
import slickdev.slick.mysql.MyProfile.api._

class AuthorTable(tag: Tag) extends Table[Author](tag, "t_author") {
  def id = column[Long]("id", O.AutoInc, O.PrimaryKey)
  def username = column[String]("")
  def age = column[Int]("age")
  def createdAt = column[Instant]("createdAt")
  def * = (id, username, age, createdAt).mapTo[Author]
}

class BookTable(tag: Tag) extends Table[Book](tag, "t_book") {
  def isbn = column[String]("isbn", O.PrimaryKey)
  def title = column[String]("title")
  def author = column[String]("author")
  def description = column[String]("description", O.SqlType("text"))
  def createdAt = column[OffsetDateTime]("created_at")
  def * = (isbn, title, author, description, createdAt).mapTo[Book]
}
