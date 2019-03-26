package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class WebfluxblockinglistSimulation extends Simulation {

  val baseUrl = "http://localhost:8080"
  val sim_users = 200

  val httpConf = http.baseUrl(baseUrl)

  val headers = Map("Accept" -> """application/json""")

  val blocking_list = repeat(30) {
    exec(http("blocking_list")
      .get("/blocking/list")
      .header("Content-Type", "application/json" ))
      .pause(1 second, 2 seconds)
  }

  val scnWebflux = scenario("now")
      .exec(blocking_list)
  setUp(
    scnWebflux.inject(rampUsers(sim_users).during(30 seconds)).protocols(httpConf),
  )
}
