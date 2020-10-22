package default // 1

import scala.concurrent.duration._

import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._

class Titanium extends Simulation { // 3

  val httpProtocol = http // 4
    .baseUrl("https://apnews-int.appspot.com") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("load-test")

  val scn = scenario("Landing") // 7
    .exec(http("/") // 8
      .get("/")) // 9

  setUp(
    scn.inject(
      rampUsersPerSec(0) to 100 during (30 seconds), // 6
      constantUsersPerSec(1000) during (3 minutes) randomized, // 5
    ).protocols(httpProtocol)
  )
}
