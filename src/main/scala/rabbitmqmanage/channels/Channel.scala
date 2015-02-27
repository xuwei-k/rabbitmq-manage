package rabbitmqmanage
package channels

import play.api.libs.functional.syntax._

final case class Channel(
  acksUncommitted        :Long,
  clientFlowBlocked      :Boolean,
  confirm                :Boolean,
  connectionDetails      :ConnectionDetails,
  consumerCount          :Long,
//  idleSince              :Option[String],
//  messageStats           :Option[MessageStats],
  messagesUnacknowledged :Long,
  messagesUncommitted    :Long,
  messagesUnconfirmed    :Long,
  name                   :String,
  node                   :String,
  number                 :Long,
  prefetchCount          :Long,
  transactional          :Boolean,
  user                   :String,
  vhost                  :String
)

object Channel {

  implicit val format: OFormat[Channel] = (
    (__ \ "acks_uncommitted").format[Long] and
    (__ \ "client_flow_blocked").format[Boolean] and
    (__ \ "confirm").format[Boolean] and
    (__ \ "connection_details").format[ConnectionDetails] and
    (__ \ "consumer_count").format[Long] and
//    (__ \ "idle_since").format[Option[String]] and
//    (__ \ "message_stats").format[Option[MessageStats]] and
    (__ \ "messages_unacknowledged").format[Long] and
    (__ \ "messages_uncommitted").format[Long] and
    (__ \ "messages_unconfirmed").format[Long] and
    (__ \ "name").format[String] and
    (__ \ "node").format[String] and
    (__ \ "number").format[Long] and
    (__ \ "prefetch_count").format[Long] and
    (__ \ "transactional").format[Boolean] and
    (__ \ "user").format[String] and
    (__ \ "vhost").format[String]
  )(apply _, Function.unlift(unapply))

}

final case class ConnectionDetails(
  name     :String,
  peerHost :String,
  peerPort :Long
)

object ConnectionDetails {

  implicit val connectionDetailsFormat: OFormat[ConnectionDetails] = (
    (__ \ "name").format[String] and
    (__ \ "peer_host").format[String] and
    (__ \ "peer_port").format[Long]
  )(ConnectionDetails.apply _, Function.unlift(ConnectionDetails.unapply))

}


final case class MessageStats(
  publish        :Long,
  publishDetails :PublishDetails
)

object MessageStats {

  implicit val messageStatsFormat: OFormat[MessageStats] = (
    (__ \ "publish").format[Long] and
    (__ \ "publish_details").format[PublishDetails]
  )(MessageStats.apply _, Function.unlift(MessageStats.unapply))

}


final case class PublishDetails(
  rate :Double
)

object PublishDetails {

  implicit val publishDetailsFormat: OFormat[PublishDetails] =
    (__ \ "rate").format[Double].inmap(PublishDetails.apply _, Function.unlift(PublishDetails.unapply))

}
