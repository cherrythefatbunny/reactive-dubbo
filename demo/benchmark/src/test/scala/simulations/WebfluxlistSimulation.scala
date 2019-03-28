package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class WebfluxlistSimulation extends Simulation {

  val baseUrl = "http://localhost:8080"
  val sim_users = 1000

  val httpConf = http.baseUrl(baseUrl)

  val headers = Map("Accept" -> """application/json""")

  val list = repeat(30) {
    exec(http("list")
      .get("/list")
      .header("Content-Type", "application/json" ))
      .pause(1 second, 2 seconds)
  }

  val scnWebflux = scenario("now")
      .exec(list)
  setUp(
    scnWebflux.inject(rampUsers(sim_users).during(30 seconds)).protocols(httpConf),
  )
}
