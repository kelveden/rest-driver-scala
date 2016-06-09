package com.kelveden.restdriverscala

import com.github.restdriver.clientdriver.ClientDriverRequest.Method
import com.github.restdriver.clientdriver.RestClientDriver._
import com.github.restdriver.clientdriver.{ClientDriverFactory, ClientDriverRequest, ClientDriverResponse, _}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}

trait RestDriven extends Suite with BeforeAndAfterAll with BeforeAndAfterEach {
  implicit val restDriverPort: Int = 0

  val factory = new ClientDriverFactory()
  var driver: ClientDriver = null

  case class HttpEntity(content: String, contentType: String)

  override def beforeAll() = {
    super.beforeAll
    driver = factory.createClientDriver(restDriverPort)
  }

  override def afterEach() = {
    super.afterEach
    driver.verify
  }

  override def afterAll() = {
    super.afterAll
    driver.shutdownQuietly
  }

  def expect(request: ClientDriverRequest, response: ClientDriverResponse) = {
    driver.addExpectation(request, response)
  }

  def request(method: Method, path: String, body: Option[HttpEntity] = None): ClientDriverRequest = {
    val builder = onRequestTo(path).withMethod(method)
    body match {
      case Some(HttpEntity(b, c)) => builder.withBody(b, c); builder
      case _ => builder
    }
  }

  def respondWith(status: Int, body: Option[HttpEntity] = None) = {
    body match {
      case Some(HttpEntity(b, c)) => giveResponse(b, c).withStatus(status)
      case _ => giveEmptyResponse().withStatus(status)
    }
  }

  def entity(content: String, contentType: String) = Some(HttpEntity(content, contentType))

  def get(path: String, body: Option[HttpEntity] = None) = request(Method.GET, path, body)
  def put(path: String, body: Option[HttpEntity] = None) = request(Method.PUT, path, body)
  def post(path: String, body: Option[HttpEntity] = None) = request(Method.POST, path, body)
  def delete(path: String, body: Option[HttpEntity] = None) = request(Method.DELETE, path, body)
}
