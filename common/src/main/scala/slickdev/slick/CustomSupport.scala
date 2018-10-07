package slickdev.slick

import helloscala.common.Configuration
import javax.sql.DataSource
import slick.ast.TypedType
import slick.jdbc.JdbcProfile

trait CustomSupport { driver: JdbcProfile =>

  import driver.api._

  trait CustomImplicits {
    val coalesceString: Seq[Rep[_]] => Rep[String] = SimpleFunction("coalesce")
    val coalesceInt: Seq[Rep[_]] => Rep[Int] = SimpleFunction("coalesce")
    val coalesceLong: Seq[Rep[_]] => Rep[Long] = SimpleFunction("coalesce")

    def coalesce[R: TypedType]: Seq[Rep[_]] => Rep[R] = SimpleFunction("coalesce")

    type FilterCriteriaType = Option[Rep[Option[Boolean]]]

    def dynamicFilter(list: Seq[FilterCriteriaType]): Rep[Option[Boolean]] =
      list
        .collect({ case Some(criteria) => criteria })
        .reduceLeftOption(_ && _)
        .getOrElse(Some(true): Rep[Option[Boolean]])

    def dynamicFilter(item: Option[Rep[Boolean]], list: Option[Rep[Boolean]]*): Rep[Boolean] =
      (item +: list).collect({ case Some(criteria) => criteria }).reduceLeftOption(_ && _).getOrElse(true: Rep[Boolean])

    def dynamicFilterOr(list: Seq[FilterCriteriaType]): Rep[Option[Boolean]] =
      list
        .collect({ case Some(criteria) => criteria })
        .reduceLeftOption(_ || _)
        .getOrElse(Some(true): Rep[Option[Boolean]])

    def createDatabase(ds: DataSource, conf: Configuration): backend.DatabaseDef = {
      val numThreads = conf.getOrElse[Int]("numThreads", 20)
      val maximumPoolSize = conf.getOrElse[Int]("maximumPoolSize", numThreads)
      val executor = AsyncExecutor(
        conf.getOrElse[String]("poolName", "default"),
        numThreads,
        numThreads,
        conf.getOrElse[Int]("queueSize", 1000),
        maximumPoolSize,
        registerMbeans = conf.getOrElse[Boolean]("registerMbeans", false)
      )
      Database.forDataSource(ds, Some(maximumPoolSize), executor)
    }

  }

}
