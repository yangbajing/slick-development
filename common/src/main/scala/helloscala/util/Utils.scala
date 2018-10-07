package helloscala.util
import scala.util.control.NonFatal

object Utils {

  def either[T <: Throwable, R](func: => R): Either[T, R] =
    try {
      val result = func
      Right(result)
    } catch {
      case NonFatal(e) =>
        Left(e.asInstanceOf[T])
    }

}
