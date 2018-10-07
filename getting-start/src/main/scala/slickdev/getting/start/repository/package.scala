package slickdev.getting.start

import slick.jdbc.MySQLProfile.api._

// #TableQuery
package object repository {
  def tBook = TableQuery[BookTable]
}
// #TableQuery
