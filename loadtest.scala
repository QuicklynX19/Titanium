package default // 1

import scala.concurrent.duration._

import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._

class Titanium extends Simulation { // 3

  val httpProtocol = http // 4
    .baseUrl("https://apnews-int.appspot.com/article/plants-health-indonesia-sumatra-trees-2a209d60c42bf0e8fcc6f8ea6daa11c7") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("load-test")

  val scn = scenario("Article") // 7
    .exec(http("/") // 8
      .get("/")) // 9

  setUp(
    scn.inject(
      rampUsersPerSec(0) to 15 during (30 seconds), // 6
      constantUsersPerSec(15) during (1 minutes) randomized, // 5
    ).protocols(httpProtocol)
  )
}
