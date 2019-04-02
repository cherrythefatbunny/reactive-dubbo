package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class future extends Simulation {

  val baseUrl = "http://localhost:9090"
  val sim_users = System.getProperty("SIM_USERS").toInt

  val httpConf = http.baseUrl(baseUrl)

  val headers = Map("Accept" -> """application/json""")

  val future_by101 = repeat(30) {
    exec(http("future")
      .get("/future/by?id=101")
      .header("Content-Type", "application/json" ))
      .pause(1 second, 2 seconds)
  }


  val scnWebflux = scenario("now")
      .exec(future_by101)
  setUp(
    scnWebflux.inject(rampUsers(sim_users).during(30 seconds)).protocols(httpConf),
  )
}
