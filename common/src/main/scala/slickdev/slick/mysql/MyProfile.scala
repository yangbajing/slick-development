package slickdev.slick.mysql

import slick.basic.Capability
import slick.jdbc.MySQLProfile
import slickdev.slick.CustomSupport

// #MyProfile
trait MyProfile extends MySQLProfile with MyJavaTimeSupport with CustomSupport {

  override protected def computeCapabilities: Set[Capability] = super.computeCapabilities

  override val api: API = new API {}

  trait API extends super.API with JavaTimeImplicits with CustomImplicits {}

  val plainAPI: PlainAPI = new PlainAPI {}

  trait PlainAPI extends PlainJavaTimeImplicits
}

object MyProfile extends MyProfile
// #MyProfile
