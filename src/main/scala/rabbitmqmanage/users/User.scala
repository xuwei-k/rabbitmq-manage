package rabbitmqmanage
package users

import play.api.libs.json.OFormat
import play.api.libs.functional.syntax._

final case class User(
  name         :String,
  passwordHash :String,
  tags         :String
) extends JsonToString[User]

object User {

  implicit val format: OFormat[User] = (
    (__ \ "name").format[String] and
    (__ \ "password_hash").format[String] and
    (__ \ "tags").format[String]
  )(apply _, Function.unlift(unapply))

}
