package rabbitmqmanage

import httpz._
import httpz.native._
import play.api.libs.json.{Json, Reads}
import rabbitmqmanage.exchanges.Exchange
import rabbitmqmanage.nodes.Node
import rabbitmqmanage.users.User

object Test {
  def main(args: Array[String]): Unit = {
    test2(if(args.nonEmpty) Some(args.toSet) else None)
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

  def test2(testNames: Option[Set[String]]) = {
    def Reads[A](implicit A: Reads[A]) = A
    val list = List[(String, Reads[_])](
      ("overview", Reads[Overview]),
      ("exchanges", Reads[List[Exchange]]),
      ("nodes", Reads[List[Node]]),
      ("users", Reads[List[User]])
    )

    testNames.fold(list)(names => list.filter{case (name, _ ) => names(name)}).foreach { case (api, reads) =>
      val request = httpz.Request(url = s"http://localhost:15672/api/$api").auth("guest", "guest")
      httpz.Core.string(request).map(play.api.libs.json.Json.parse).map { json =>
        println("-" * 100)
        println(api)
        println("-" * 100)
        println(Json.prettyPrint(json))
        val obj = json.validate(reads)
        println(obj.isSuccess)
        obj.recoverTotal(
          _.errors.foreach(println)
        )
      }.interpret.leftMap(_.printStackTrace)
    }
  }
}
