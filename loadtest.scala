package default

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Titanium extends Simulation {

        val web = http
                .baseUrl("https://apnews-int.appspot.com")
                .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .acceptLanguageHeader("en-US,en;q=0.5")
                .acceptEncodingHeader("gzip, deflate")
                .userAgentHeader("load-test")

        val landing = scenario("Landing").exec(http("/").get("/"))

        setUp(content.inject(
                rampUsersPerSec(0) to(200) during(60 seconds),
                constantUsersPerSec(200) during(20 minutes) randomized
        ))
        .protocols(web);
}