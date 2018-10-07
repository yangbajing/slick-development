package helloscala.exception
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

object ErrCodes {
  val UNKNOWN = -1
  val ACCEPTED = 202
  val BAD_REQUEST = 400
  val UNAUTHORIZED = 401
  val NO_CONTENT = 402
  val FORBIDDEN = 403
  val NOT_FOUND = 404
  val NOT_FOUND_CONFIG = 404001
  val CONFLICT = 409
  val INTERNAL_ERROR = 500
  val NOT_IMPLEMENTED = 501
}

@JsonIgnoreProperties(value = Array("suppressed", "localizedMessage", "message", "stackTrace", "cause"))
abstract class HSException(errCode: Int, errMsg: String, cause: Throwable) extends RuntimeException(errMsg, cause) {
  def httpStatus: Int
}

case class HSAcceptedWarning(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.ACCEPTED,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.ACCEPTED
}

case class HSBadRequestException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.BAD_REQUEST,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.BAD_REQUEST
}

case class HSUnauthorizedException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.UNAUTHORIZED,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.UNAUTHORIZED
}

case class HSNoContentException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.NO_CONTENT,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.NO_CONTENT
}

case class HSForbiddenException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.FORBIDDEN,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.FORBIDDEN
}

case class HSNotFoundException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.NOT_FOUND,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.NOT_FOUND
}

case class HSConfigurationException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.NOT_FOUND_CONFIG,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.NOT_FOUND
}

case class HSConflictException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.CONFLICT,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.CONFLICT
}

case class HSNotImplementedException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.NOT_IMPLEMENTED,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.NOT_IMPLEMENTED
}

case class HSInternalErrorException(
    errMsg: String,
    data: AnyRef = null,
    errCode: Int = ErrCodes.INTERNAL_ERROR,
    cause: Throwable = null)
    extends HSException(errCode, errMsg, cause) {
  override val httpStatus = ErrCodes.INTERNAL_ERROR
}
