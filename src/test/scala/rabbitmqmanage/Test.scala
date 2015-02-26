package rabbitmqmanage

import httpz._, native._
import play.api.libs.json.{Reads, JsValue, Json}
import rabbitmqmanage.exchanges.Exchange
import rabbitmqmanage.users.User

object Test {
  def main(args: Array[String]): Unit = {
    test2()
  }

  def test1() = {
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
      json.interpret
    }
  }

  def test2() = {
    def Reads[A](implicit A: Reads[A]) = A
    val list = List[(String, Reads[_])](
      ("overview", Reads[Overview]),
      ("exchanges", Reads[List[Exchange]]),
      ("users", Reads[List[User]])
    )

    list.foreach { case (api, reads) =>
      val request = httpz.Request(url = s"http://localhost:15672/api/$api").auth("guest", "guest")
      httpz.Core.string(request).map(play.api.libs.json.Json.parse).map { json =>
        println("-" * 100)
        println(api)
        println("-" * 100)
        println(Json.prettyPrint(json))
        val obj = json.validate(reads)
        println(obj.isSuccess)
        obj.recoverTotal(println)
      }.interpret
    }
  }
}
