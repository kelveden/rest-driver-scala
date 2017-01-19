package com.kelveden.restdriverscala

import org.scalatest.{FunSpec, Matchers}
import com.github.restdriver.serverdriver.RestServerDriver._
import PortFinder._

class RestDrivenTest extends FunSpec with Matchers with RestDriven with RestDrivenMatchers {
  override val restDriverPort = getFreePort
  val baseUrl = s"http://localhost:$restDriverPort"

  describe("expected request builder") {
    it("can build a GET request") {
      expect(onGetTo("/my/url"), respondWith(666))

      val response = get(s"$baseUrl/my/url")

      response should haveStatus(666)
    }

    it("can build a GET request with body") {
      expect(
        onGetTo("/my/url", entity = ("mycontent", "text/plain")),
        respondWith(666)
      )

      val response = get(s"$baseUrl/my/url", body("mycontent", "text/plain"))

      response should haveStatus(666)
    }

    it("can build a GET request with headers") {
      expect(
        onGetTo("/my/url", headers = Map("myheader" -> "myvalue")),
        respondWith(666)
      )

      val response = get(s"$baseUrl/my/url", header("myheader", "myvalue"))

      response should haveStatus(666)
    }

    it("can build a GET request with params") {
      expect(
        onGetTo("/my/url", params = Map("myparam" -> "myvalue")),
        respondWith(666)
      )

      val response = get(s"$baseUrl/my/url?myparam=myvalue")

      response should haveStatus(666)
    }

    it("can build a PUT request") {
      expect(onPutTo("/my/url"), respondWith(666))

      val response = put(s"$baseUrl/my/url")

      response should haveStatus(666)
    }

    it("can build a PUT request with body") {
      expect(
        onPutTo("/my/url", entity = ("mycontent", "text/plain")),
        respondWith(666)
      )

      val response = put(s"$baseUrl/my/url", body("mycontent", "text/plain"))

      response should haveStatus(666)
    }

    it("can build a PUT request with headers") {
      expect(
        onPutTo("/my/url", headers = Map("myheader" -> "myvalue")),
        respondWith(666)
      )

      val response = put(s"$baseUrl/my/url", header("myheader", "myvalue"))

      response should haveStatus(666)
    }

    it("can build a PUT request with params") {
      expect(
        onPutTo("/my/url", params = Map("myparam" -> "myvalue")),
        respondWith(666)
      )

      val response = put(s"$baseUrl/my/url?myparam=myvalue")

      response should haveStatus(666)
    }

    it("can build a POST request") {
      expect(onPostTo("/my/url"), respondWith(666))

      val response = post(s"$baseUrl/my/url")

      response should haveStatus(666)
    }

    it("can build a POST request with body") {
      expect(
        onPostTo("/my/url", entity = ("mycontent", "text/plain")),
        respondWith(666)
      )

      val response = post(s"$baseUrl/my/url", body("mycontent", "text/plain"))

      response should haveStatus(666)
    }

    it("can build a POST request with headers") {
      expect(
        onPostTo("/my/url", headers = Map("myheader" -> "myvalue")),
        respondWith(666)
      )

      val response = post(s"$baseUrl/my/url", header("myheader", "myvalue"))

      response should haveStatus(666)
    }

    it("can build a POST request with params") {
      expect(
        onPostTo("/my/url", params = Map("myparam" -> "myvalue")),
        respondWith(666)
      )

      val response = post(s"$baseUrl/my/url?myparam=myvalue")

      response should haveStatus(666)
    }

    it("can build a DELETE request") {
      expect(onDeleteTo("/my/url"), respondWith(666))

      val response = delete(s"$baseUrl/my/url")

      response should haveStatus(666)
    }

    it("can build a DELETE request with body") {
      expect(
        onDeleteTo("/my/url", entity = ("mycontent", "text/plain")),
        respondWith(666)
      )

      val response = delete(s"$baseUrl/my/url", body("mycontent", "text/plain"))

      response should haveStatus(666)
    }

    it("can build a DELETE request with headers") {
      expect(
        onDeleteTo("/my/url", headers = Map("myheader" -> "myvalue")),
        respondWith(666)
      )

      val response = delete(s"$baseUrl/my/url", header("myheader", "myvalue"))

      response should haveStatus(666)
    }

    it("can build a DELETE request with params") {
      expect(
        onDeleteTo("/my/url", params = Map("myparam" -> "myvalue")),
        respondWith(666)
      )

      val response = delete(s"$baseUrl/my/url?myparam=myvalue")

      response should haveStatus(666)
    }

    it("can build a request with ad-hoc method") {
      expect(onRequestTo("OPTIONS", "/my/url"), respondWith(666))

      val response = options(s"$baseUrl/my/url")

      response should haveStatus(666)
    }

    it("can build an ad-hoc request with body") {
      expect(
        onRequestTo("PUT", "/my/url", entity = ("mycontent", "text/plain")),
        respondWith(666)
      )

      val response = put(s"$baseUrl/my/url", body("mycontent", "text/plain"))

      response should haveStatus(666)
    }

    it("can build an ad-hoc request with headers") {
      expect(
        onRequestTo("GET", "/my/url", headers = Map("myheader" -> "myvalue")),
        respondWith(666)
      )

      val response = get(s"$baseUrl/my/url", header("myheader", "myvalue"))

      response should haveStatus(666)
    }

    it("can build an ad-hoc request with params") {
      expect(
        onRequestTo("GET", "/my/url", params = Map("myparam" -> "myvalue")),
        respondWith(666)
      )

      val response = get(s"$baseUrl/my/url?myparam=myvalue")

      response should haveStatus(666)
    }
  }

  describe("expected response builder") {
    it("can be used to specify the status code to respond with") {
      expect(onGetTo("/my/url"), respondWith(666))

      val response = get(s"$baseUrl/my/url")

      response should haveStatus(666)
    }

    it("can be used to specify the entity to respond with") {
      expect(
        onGetTo("/my/url"),
        respondWith(666, entity = ("mycontent", "text/plain"))
      )

      val response = get(s"$baseUrl/my/url")

      response should haveBodyContent("mycontent")
    }

    it("can be used to specify the headers to include in the response") {
      expect(
        onGetTo("/my/url"),
        respondWith(666, headers = Map("myheader" -> "myvalue"))
      )

      val response = get(s"$baseUrl/my/url")

      response should haveHeader("myheader", "myvalue")
    }
  }
}
