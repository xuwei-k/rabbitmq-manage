package rabbitmqmanage
package nodes

import play.jsonext._
import play.api.libs.json.JsValue

final case class Node(
//  configFiles        :List[String],
//  dbDir              :String,
  diskFree           :Long,
  diskFreeAlarm      :Boolean,
//  diskFreeDetails    :Map[String, JsValue],
  diskFreeLimit      :Long,
//  enabledPlugins     :List[String],
  fdTotal            :Long,
  fdUsed             :Long,
//  fdUsedDetails      :Map[String, JsValue],
//  logFile            :String,
  memAlarm           :Boolean,
  memLimit           :Long,
  memUsed            :Long,
//  memUsedDetails     :Map[String, JsValue],
  name               :String,
//  netTicktime        :Long,
  osPid              :String,
  partitions         :List[JsValue],
  procTotal          :Long,
  procUsed           :Long,
//  procUsedDetails    :Map[String, JsValue],
  processors         :Long,
//  ratesMode          :String,
  runQueue           :Long,
  running            :Boolean,
//  saslLogFile        :String,
  socketsTotal       :Long,
  socketsUsed        :Long,
//  socketsUsedDetails :Map[String, JsValue],
  tpe                :String,
  uptime             :Long
)

object Node {

  implicit val format: OFormat[Node] = CaseClassFormats(apply _, unapply _)(
//     "config_files",
//     "db_dir",
     "disk_free",
     "disk_free_alarm",
//     "disk_free_details",
     "disk_free_limit",
//     "enabled_plugins",
     "fd_total",
     "fd_used",
//     "fd_used_details",
//     "log_file",
     "mem_alarm",
     "mem_limit",
     "mem_used",
//     "mem_used_details",
     "name",
//     "net_ticktime",
     "os_pid",
     "partitions",
     "proc_total",
     "proc_used",
//     "proc_used_details",
     "processors",
//     "rates_mode",
     "run_queue",
     "running",
//     "sasl_log_file",
     "sockets_total",
     "sockets_used",
//     "sockets_used_details",
     "type",
     "uptime"
  )

}

