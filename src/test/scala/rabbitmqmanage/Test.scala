package rabbitmqmanage

import httpz._, native._
import play.api.libs.json.Json

object Test {
  def main(args: Array[String]): Unit = {
    val apis = (
      "overview" ::
      "cluster-name" ::
      "nodes" ::
      "extensions" ::
      "definitions" ::
      "connections" ::
      "channels" ::
      "exchanges" ::
      "queues" ::
      "bindings" ::
      "vhosts" ::
      "users" ::
      "whoami" ::
      "permissions" ::
      "parameters" ::
      "policies" ::
      Nil
    )

    apis.foreach { api =>
      val request = httpz.Request(url = s"http://localhost:15672/api/$api").auth("guest", "guest")
      val json = httpz.Core.string(request).map(play.api.libs.json.Json.parse).map { j =>
        println("-" * 100)
        println(api)
        println("-" * 100)
        println(Json.prettyPrint(j))
        println("-" * 100)
      }
      println(json.interpret)
    }
  }

  def overview() = {
    val request = httpz.Request(url = "http://localhost:15672/api/overview").auth("guest", "guest")
    val json = httpz.Core.string(request).map(play.api.libs.json.Json.parse).map{ json =>
      println(Json.prettyPrint(json))
      json.as[Overview]
    }
    println(json.interpret)
  }
}
