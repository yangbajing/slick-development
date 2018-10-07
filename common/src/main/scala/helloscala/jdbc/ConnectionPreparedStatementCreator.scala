package helloscala.jdbc

import java.sql.{Connection, PreparedStatement}

import helloscala.util.StringUtils

@FunctionalInterface
trait ConnectionPreparedStatementCreator {
  def apply(conn: Connection): PreparedStatement
}

class ConnectionPreparedStatementCreatorImpl(sql: String, namedSql: String = "")
    extends ConnectionPreparedStatementCreator {
  def getSql: String = if (StringUtils.isNoneBlank(namedSql)) namedSql else sql

  override def apply(conn: Connection): PreparedStatement = conn.prepareStatement(sql)
}
