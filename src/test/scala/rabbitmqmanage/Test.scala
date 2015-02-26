package rabbitmqmanage

import httpz._, native._

object Test {
  def main(args: Array[String]): Unit = {
    val request = httpz.Request(url = "http://localhost:15672/api/overview").auth("guest", "guest")
    val json = httpz.Core.string(request).map(play.api.libs.json.Json.parse).map{ json =>
      println(json)
      json.as[Overview]
    }
    println(json.interpret)
  }
}
