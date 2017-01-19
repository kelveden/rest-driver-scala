package com.kelveden.restdriverscala

import org.scalatest.{FunSpec, Matchers}
import PortFinder._
import com.github.restdriver.serverdriver.RestServerDriver.get

class RestDrivenMatchersTest extends FunSpec with Matchers with RestDriven with RestDrivenMatchers {
  override val restDriverPort = getFreePort
  val baseUrl = s"http://localhost:$restDriverPort"

  describe("haveBody matcher") {
    it("matches the raw string response body against a string content") {
      expect(onGetTo("/my/url"), respondWith(666, entity = ("mycontent", "text/plain")))

      val response = get(s"$baseUrl/my/url")

      response should haveBodyContent("mycontent")
    }

    it("does not match if the response body differs from the specified content") {
      expect(onGetTo("/my/url"), respondWith(666, entity = ("bollox", "text/plain")))

      val response = get(s"$baseUrl/my/url")

      response shouldNot haveBodyContent("mycontent")
    }
  }

  describe("haveBodyThat matcher") {
    it("matches the raw string response body with a string matcher") {
      expect(onGetTo("/my/url"), respondWith(666, entity = ("mycontent", "text/plain")))

      val response = get(s"$baseUrl/my/url")

      response should haveBodyThat(startWith("mycont"))
    }

    it("does not match if the response body if the specified matcher does not return a match") {
      expect(onGetTo("/my/url"), respondWith(666, entity = ("bollox", "text/plain")))

      val response = get(s"$baseUrl/my/url")

      response shouldNot haveBodyThat(endWith("mycontent"))
    }
  }

  describe("haveStatus matcher") {
    it("matches on status code of response") {
      expect(onGetTo("/my/url"), respondWith(201))

      val response = get(s"$baseUrl/my/url")

      response should haveStatus(201)
    }

    it("does not match if status code differs") {
      expect(onGetTo("/my/url"), respondWith(666))

      val response = get(s"$baseUrl/my/url")

      response shouldNot haveStatus(201)
    }
  }

  describe("haveHeader matcher") {
    it("asserts that a specific header exists in the response") {
      expect(onGetTo("/my/url"), respondWith(201).withHeader("myheader", "somevalue"))

      val response = get(s"$baseUrl/my/url")

      response should haveHeader("myheader")
    }

    it("will not match if the specified header is missing in the response") {
      expect(onGetTo("/my/url"), respondWith(201))

      val response = get(s"$baseUrl/my/url")

      response shouldNot haveHeader("myheader")
    }
  }

  describe("haveHeaderWithValue matcher") {
    it("asserts that a specific header with the specified value exists in the response") {
      expect(onGetTo("/my/url"), respondWith(201).withHeader("myheader", "myvalue"))

      val response = get(s"$baseUrl/my/url")

      response should haveHeader("myheader", "myvalue")
    }

    it("will not match if the specified header is missing in the response") {
      expect(onGetTo("/my/url"), respondWith(201))

      val response = get(s"$baseUrl/my/url")

      response shouldNot haveHeader("myheader", "myvalue")
    }
  }
}
