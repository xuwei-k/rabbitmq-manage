package rabbitmqmanage
package definitions

import play.api.libs.functional.syntax._
import play.api.libs.json.JsValue

final case class Definitions(
  bindings      :List[JsValue],
  exchanges     :List[JsValue],
  parameters    :List[JsValue],
  permissions   :List[Permission],
  policies      :List[JsValue],
  queues        :List[JsValue],
  rabbitVersion :String,
  users         :List[User],
  vhosts        :List[Vhost]
)

object Definitions {

  implicit val JsValueFormat: OFormat[Definitions] = (
    (__ \ "bindings").format[List[JsValue]] and
    (__ \ "exchanges").format[List[JsValue]] and
    (__ \ "parameters").format[List[JsValue]] and
    (__ \ "permissions").format[List[Permission]] and
    (__ \ "policies").format[List[JsValue]] and
    (__ \ "queues").format[List[JsValue]] and
    (__ \ "rabbit_version").format[String] and
    (__ \ "users").format[List[User]] and
    (__ \ "vhosts").format[List[Vhost]]
  )(apply _, Function.unlift(unapply))

}


final case class User(
  name         :String,
  passwordHash :String,
  tags         :String
)

object User {

  implicit val format: OFormat[User] = (
    (__ \ "name").format[String] and
    (__ \ "password_hash").format[String] and
    (__ \ "tags").format[String]
  )(apply _, Function.unlift(unapply))

}

final case class Vhost(name :String)

object Vhost{

  implicit val format: OFormat[Vhost] =
    (__ \ "name").format[String].inmap(apply _, Function.unlift(unapply))

}

final case class Permission(
  configure :String,
  read      :String,
  user      :String,
  vhost     :String,
  write     :String
)

object Permission {

  implicit val JsValueFormat: OFormat[Permission] = (
    (__ \ "configure").format[String] and
    (__ \ "read").format[String] and
    (__ \ "user").format[String] and
    (__ \ "vhost").format[String] and
    (__ \ "write").format[String]
  )(apply _, Function.unlift(unapply))

}
