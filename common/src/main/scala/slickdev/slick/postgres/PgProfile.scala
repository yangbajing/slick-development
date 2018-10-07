package slickdev.slick.postgres

import com.github.tminglei.slickpg.agg.PgAggFuncSupport
import com.github.tminglei.slickpg.lobj.LargeObjectSupport
import com.github.tminglei.slickpg.str.PgStringSupport
import com.github.tminglei.slickpg.window.PgWindowFuncSupport
import com.github.tminglei.slickpg.{ArraySupport, ExPostgresProfile, PgDate2Support, PgHStoreSupport}
import slick.basic.Capability
import slick.jdbc.JdbcCapabilities
import slickdev.slick.CustomSupport

trait PgProfile
    extends ExPostgresProfile
    with PgDate2Support
    with PgHStoreSupport
    with PgStringSupport
    with LargeObjectSupport
    with ArraySupport
    with PgJacksonJsonSupport
    with PgAggFuncSupport
    with PgWindowFuncSupport
    with CustomSupport {

  override protected def computeCapabilities: Set[Capability] =
    super.computeCapabilities + JdbcCapabilities.insertOrUpdate

  override val pgjson = "jsonb"

  override val api: API = new API {}

  trait API
      extends super.API
      with JsonImplicits
      with HStoreImplicits
      with PgStringImplicits
      with ArrayImplicits
      with DateTimeImplicits
      with GeneralAggFunctions
      with StatisticsAggFunctions
      with OrderedSetAggFunctions
      with HypotheticalSetAggFunctions
      with WindowFunctions
      with CustomImplicits {}

  val plainApi: PlainAPI = new PlainAPI {}

  trait PlainAPI
      extends ByteaPlainImplicits
      with JacksonJsonPlainImplicits
      with SimpleHStorePlainImplicits
      with SimpleArrayPlainImplicits
      with Date2DateTimePlainImplicits
}

object PgProfile extends PgProfile
