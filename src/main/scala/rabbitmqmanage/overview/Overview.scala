package rabbitmqmanage

import play.api.libs.functional.syntax._
import play.api.libs.json.JsObject
import rabbitmqmanage.Overview.MessageStats

final case class Listener(
  ipAddress :String,
  node      :String,
  port      :Long,
  protocol  :String
) extends JsonToString[Listener]

object Listener {

  implicit val format: OFormat[Listener] = (
    (__ \ "ip_address").format[String] and
    (__ \ "node").format[String] and
    (__ \ "port").format[Long] and
    (__ \ "protocol").format[String]
  )(apply _, Function.unlift(unapply))

}


final case class ExchangeType(
  description :String,
  enabled     :Boolean,
  name        :String
) extends JsonToString[ExchangeType]

object ExchangeType{

  implicit val format: OFormat[ExchangeType] = (
    (__ \ "description").format[String] and
    (__ \ "enabled").format[Boolean] and
    (__ \ "name").format[String]
  )(apply _, Function.unlift(unapply))

}

final case class Overview(
  clusterName       :String,
  contexts          :List[Context],
  erlangFullVersion :String,
  erlangVersion     :String,
  exchangeTypes     :List[ExchangeType],
  listeners         :List[Listener],
  managementVersion :String,
  messageStats      :MessageStats, // TODO
  node              :String,
  objectTotals      :ObjectTotals,
  queueTotals       :QueueTotals,
  rabbitmqVersion   :String,
  statisticsDbNode  :String,
  statisticsLevel   :Option[String]
) extends JsonToString[Overview]

object Overview {
  type MessageStats = JsObject

  implicit val overviewFormat: OFormat[Overview] = (
    (__ \ "cluster_name").format[String] and
    (__ \ "contexts").format[List[Context]] and
    (__ \ "erlang_full_version").format[String] and
    (__ \ "erlang_version").format[String] and
    (__ \ "exchange_types").format[List[ExchangeType]] and
    (__ \ "listeners").format[List[Listener]] and
    (__ \ "management_version").format[String] and
    (__ \ "message_stats").format[MessageStats] and
    (__ \ "node").format[String] and
    (__ \ "object_totals").format[ObjectTotals] and
    (__ \ "queue_totals").format[QueueTotals] and
    (__ \ "rabbitmq_version").format[String] and
    (__ \ "statistics_db_node").format[String] and
    (__ \ "statistics_level").formatNullable[String]
  )(apply _, Function.unlift(unapply))
}

final case class Context(
  description :String,
  node        :String,
  path        :String,
  port        :Long
) extends JsonToString[Context]

object Context {

  implicit val ContextFormat: OFormat[Context] = (
    (__ \ "description").format[String] and
    (__ \ "node").format[String] and
    (__ \ "path").format[String] and
    (__ \ "port").format[Long]
  )(apply _, Function.unlift(unapply))

}

final case class ObjectTotals(
  channels    :Long,
  connections :Long,
  consumers   :Long,
  exchanges   :Long,
  queues      :Long
) extends JsonToString[ObjectTotals]

object ObjectTotals {

  implicit val objectTotalsFormat: OFormat[ObjectTotals] = (
    (__ \ "channels").format[Long] and
    (__ \ "connections").format[Long] and
    (__ \ "consumers").format[Long] and
    (__ \ "exchanges").format[Long] and
    (__ \ "queues").format[Long]
  )(apply _, Function.unlift(unapply))

}

final case class QueueTotals(
  messages                      :Long,
  messagesDetails               :MessagesDetails,
  messagesReady                 :Long,
  messagesReadyDetails          :MessagesReadyDetails,
  messagesUnacknowledged        :Long,
  messagesUnacknowledgedDetails :MessagesUnacknowledgedDetails
) extends JsonToString[QueueTotals]

object QueueTotals {

  implicit val queueTotalsFormat: OFormat[QueueTotals] = (
    (__ \ "messages").format[Long] and
    (__ \ "messages_details").format[MessagesDetails] and
    (__ \ "messages_ready").format[Long] and
    (__ \ "messages_ready_details").format[MessagesReadyDetails] and
    (__ \ "messages_unacknowledged").format[Long] and
    (__ \ "messages_unacknowledged_details").format[MessagesUnacknowledgedDetails]
  )(QueueTotals.apply _, Function.unlift(QueueTotals.unapply))

}


final case class MessagesUnacknowledgedDetails(
  rate :Long
) extends JsonToString[MessagesUnacknowledgedDetails]

object MessagesUnacknowledgedDetails {

  implicit val messagesUnacknowledgedDetailsFormat: OFormat[MessagesUnacknowledgedDetails] =
    (__ \ "rate").format[Long].inmap(MessagesUnacknowledgedDetails.apply _, Function.unlift(MessagesUnacknowledgedDetails.unapply))

}


final case class MessagesDetails(
  rate :Long
) extends JsonToString[MessagesDetails]

object MessagesDetails {

  implicit val messagesDetailsFormat: OFormat[MessagesDetails] =
    (__ \ "rate").format[Long].inmap(MessagesDetails.apply _, Function.unlift(MessagesDetails.unapply))

}


final case class MessagesReadyDetails(
  rate :Long
) extends JsonToString[MessagesReadyDetails]

object MessagesReadyDetails {

  implicit val messagesReadyDetailsFormat: OFormat[MessagesReadyDetails] =
    (__ \ "rate").format[Long].inmap(MessagesReadyDetails.apply _, Function.unlift(MessagesReadyDetails.unapply))

}
