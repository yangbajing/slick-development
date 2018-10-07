package slickdev.slick.mysql

import java.sql.{Date, Time, Timestamp}
import java.time._

import helloscala.util.TimeUtils
import slick.jdbc.{GetResult, JdbcType, MySQLProfile, SetParameter}

trait MyJavaTimeSupport {
  driver: MySQLProfile =>

  import driver.api._

  // #JavaTimeImplicits
  trait JavaTimeImplicits {
    implicit val localDateTimeJdbcType: JdbcType[LocalDateTime] =
      MappedColumnType.base[LocalDateTime, Timestamp](Timestamp.valueOf, _.toLocalDateTime)
    implicit val localDateJdbcType: JdbcType[LocalDate] =
      MappedColumnType.base[LocalDate, Date](Date.valueOf, _.toLocalDate)
    implicit val localTimeJdbcType: JdbcType[LocalTime] =
      MappedColumnType.base[LocalTime, Time](Time.valueOf, _.toLocalTime)
    implicit val instantJdbcType: JdbcType[Instant] =
      MappedColumnType.base[Instant, Timestamp](Timestamp.from, _.toInstant)
    implicit val offsetDateTimeJdbcType: JdbcType[OffsetDateTime] = MappedColumnType.base[OffsetDateTime, Timestamp](
      odt => Timestamp.from(odt.toInstant),
      _.toInstant.atOffset(TimeUtils.ZONE_CHINA_OFFSET))
  }
  // #JavaTimeImplicits

  // #PlainJavaTimeImplicits
  trait PlainJavaTimeImplicits {
    implicit val getLocalDateTime: GetResult[LocalDateTime] =
      GetResult[LocalDateTime](pr => pr.nextTimestamp().toLocalDateTime)
    implicit val getLocalDateTimeOption: GetResult[Option[LocalDateTime]] =
      GetResult[Option[LocalDateTime]](pr => pr.nextTimestampOption().map(_.toLocalDateTime))
    implicit val setLocalDateTime: SetParameter[LocalDateTime] = (v, pr) => pr.setTimestamp(Timestamp.valueOf(v))
    implicit val setLocalDateTimeOption: SetParameter[Option[LocalDateTime]] = (v, pr) =>
      pr.setTimestampOption(v.map(Timestamp.valueOf))

    implicit val getLocalDate: GetResult[LocalDate] = GetResult[LocalDate](pr => pr.nextDate().toLocalDate)
    implicit val getLocalDateOption: GetResult[Option[LocalDate]] =
      GetResult[Option[LocalDate]](pr => pr.nextDateOption().map(_.toLocalDate))
    implicit val setLocalDate: SetParameter[LocalDate] = (v, pr) => pr.setDate(Date.valueOf(v))
    implicit val setLocalDateOption: SetParameter[Option[LocalDate]] = (v, pr) => pr.setDateOption(v.map(Date.valueOf))

    implicit val getLocalTime: GetResult[LocalTime] = GetResult[LocalTime](pr => pr.nextTime().toLocalTime)
    implicit val getLocalTimeOption: GetResult[Option[LocalTime]] =
      GetResult[Option[LocalTime]](pr => pr.nextTimeOption().map(_.toLocalTime))
    implicit val setLocalTime: SetParameter[LocalTime] = (v, pr) => pr.setTime(Time.valueOf(v))
    implicit val setLocalTimeOption: SetParameter[Option[LocalTime]] = (v, pr) => pr.setTimeOption(v.map(Time.valueOf))

    implicit val getInstant: GetResult[Instant] = GetResult[Instant](pr => pr.nextTimestamp().toInstant)
    implicit val getInstantOption: GetResult[Option[Instant]] =
      GetResult[Option[Instant]](pr => pr.nextTimestampOption().map(_.toInstant))
    implicit val setInstant: SetParameter[Instant] = (v, pr) => pr.setTimestamp(Timestamp.from(v))
    implicit val setInstantOption: SetParameter[Option[Instant]] = (v, pr) =>
      pr.setTimestampOption(v.map(Timestamp.from))

    implicit val getOffsetDateTime: GetResult[OffsetDateTime] =
      GetResult[OffsetDateTime](pr => pr.nextTimestamp().toInstant.atOffset(TimeUtils.ZONE_CHINA_OFFSET))
    implicit val getOffsetDateTimeOption: GetResult[Option[OffsetDateTime]] =
      GetResult[Option[OffsetDateTime]](pr =>
        pr.nextTimestampOption().map(_.toInstant.atOffset(TimeUtils.ZONE_CHINA_OFFSET)))
    implicit val setOffsetDateTime: SetParameter[OffsetDateTime] = (v, pr) =>
      pr.setTimestamp(Timestamp.from(v.toInstant))
    implicit val setOffsetDateTimeOption: SetParameter[Option[OffsetDateTime]] = (v, pr) =>
      pr.setTimestampOption(v.map(odt => Timestamp.from(odt.toInstant)))
  }
  // #PlainJavaTimeImplicits

}
