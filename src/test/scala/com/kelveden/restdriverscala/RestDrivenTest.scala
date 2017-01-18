package com.kelveden.restdriverscala

import org.scalatest.{FunSpec, Matchers}
import com.github.restdriver.serverdriver.RestServerDriver._
import PortFinder._

class RestDrivenTest extends FunSpec with Matchers with RestDriven {
  override val restDriverPort = getFreePort
  val baseUrl = s"http://localhost:$restDriverPort"

  describe("expected request builder") {
    it("can build a GET request") {
      expect(onGetTo("/my/url"), respondWith(666))

      val response = get(s"$baseUrl/my/url")

      response.getStatusCode should equal(666)
    }

    it("can build a PUT request") {
      expect(onPutTo("/my/url"), respondWith(666))

      val response = put(s"$baseUrl/my/url")

      response.getStatusCode should equal(666)
    }

    it("can build a POST request") {
      expect(onPostTo("/my/url"), respondWith(666))

      val response = post(s"$baseUrl/my/url")

      response.getStatusCode should equal(666)
    }

    it("can build a DELETE request") {
      expect(onDeleteTo("/my/url"), respondWith(666))

      val response = delete(s"$baseUrl/my/url")

      response.getStatusCode should equal(666)
    }

    it("can build a request with ad-hoc method") {
      expect(onRequestTo("OPTIONS", "/my/url"), respondWith(666))

      val response = options(s"$baseUrl/my/url")

      response.getStatusCode should equal(666)
    }
  }

  describe("expected response builder") {
    it("can be used to specify the status code to respond with") {
      expect(onGetTo("/my/url"), respondWith(666))

      val response = get(s"$baseUrl/my/url")

      response.getStatusCode should equal(666)
    }

    it("can be used to specify the entity to respond with") {
      expect(
        onGetTo("/my/url"),
        respondWith(666, entity("mycontent", "text/plain"))
      )

      val response = get(s"$baseUrl/my/url")

      response.getContent should equal("mycontent")
    }
  }
}
