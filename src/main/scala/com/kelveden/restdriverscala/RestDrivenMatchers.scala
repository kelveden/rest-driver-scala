package com.kelveden.restdriverscala

import com.github.restdriver.serverdriver.http.response.Response
import org.scalatest.matchers._
import org.scalatest.words.MatcherWords._
import scala.collection.JavaConverters._

trait RestDrivenMatchers {
  class HasBody(bodyMatcher: Matcher[String]) extends Matcher[Response] {
    def apply(left: Response) = {
      val body = left.getContent
      val result = bodyMatcher.apply(body).matches

      MatchResult(result,
        s"""Body "$body" did not match:""",
        s"""Body "$body" matched"""
      )
    }
  }

  def haveBodyThat(bodyMatcher: Matcher[String]) = new HasBody(bodyMatcher)
  def haveBodyContent(content: String) = new HasBody(equal(content))

  class HasStatusCode(expected: Int) extends Matcher[Response] {
    def apply(left: Response) = {
      val statusCode = left.getStatusCode

      MatchResult(statusCode == expected,
        s"""Status code $statusCode did not match expected status $expected""",
        s"""Status code $statusCode matched"""
      )
    }
  }

  def haveStatus(status: Int) = new HasStatusCode(status)

  class HasHeader(name: String) extends Matcher[Response] {
    def apply(left: Response) = {
      MatchResult(left.getHeaders(name).size > 0,
        s"""Header "$name" was not present in response headers ${left.getHeaders}""",
        s"""Header "$name" was present in response"""
      )
    }
  }

  class HasHeaderWithValue(name: String, value: String) extends Matcher[Response] {
    def apply(left: Response) = {
        MatchResult(left.getHeaders(name).asScala.exists(_.getValue == value),
        s"""Header "$name: $value" was not present in response headers ${left.getHeaders}""",
        s"""Header "$name: $value" was present in response"""
      )
    }
  }

  def haveHeader(name: String) = new HasHeader(name)
  def haveHeader(name: String, value: String) = new HasHeaderWithValue(name, value)
}

object RestDrivenMatchers extends RestDrivenMatchers