package rabbitmqmanage
package exchanges

import play.api.libs.functional.syntax._
import play.api.libs.json.JsObject
import rabbitmqmanage.exchanges.Exchange.Arguments

final case class Exchange(
  arguments  :Arguments,
  autoDelete :Boolean,
  durable    :Boolean,
  internal   :Boolean,
  name       :String,
  tpe        :String,
  vhost      :String
) extends JsonToString[Exchange]

object Exchange {
  type Arguments = JsObject

  implicit val foramt: OFormat[Exchange] = (
    (__ \ "arguments").format[Arguments] and
    (__ \ "auto_delete").format[Boolean] and
    (__ \ "durable").format[Boolean] and
    (__ \ "internal").format[Boolean] and
    (__ \ "name").format[String] and
    (__ \ "type").format[String] and
    (__ \ "vhost").format[String]
  )(apply _, Function.unlift(unapply))
}
