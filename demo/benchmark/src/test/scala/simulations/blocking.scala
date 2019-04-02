package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class blocking extends Simulation {

  val baseUrl = "http://localhost:8080"
  val sim_users = System.getProperty("SIM_USERS").toInt

  val httpConf = http.baseUrl(baseUrl)

  val headers = Map("Accept" -> """application/json""")

  val blocking_by101 = repeat(30) {
    exec(http("blocking")
      .get("/blocking/by?id=101")
      .header("Content-Type", "application/json" ))
      .pause(1 second, 2 seconds)
  }


  val scnWebflux = scenario("now")
      .exec(blocking_by101)
  setUp(
    scnWebflux.inject(rampUsers(sim_users).during(30 seconds)).protocols(httpConf),
  )
}
