package rabbitmqmanage

import play.api.libs.json.{Json, Writes}

abstract class JsonToString[A](implicit A: Writes[A]) { self: A =>
  override final def toString =
    Json.prettyPrint(A.writes(this))
}
