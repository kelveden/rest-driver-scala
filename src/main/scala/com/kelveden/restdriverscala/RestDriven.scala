package com.kelveden.restdriverscala

import com.github.restdriver.clientdriver.ClientDriverRequest.Method
import com.github.restdriver.clientdriver.RestClientDriver
import com.github.restdriver.clientdriver.{ClientDriverFactory, ClientDriverRequest, ClientDriverResponse, _}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}

trait RestDriven extends Suite with BeforeAndAfterAll with BeforeAndAfterEach {
  val restDriverPort: Int

  protected val factory = new ClientDriverFactory()
  protected var driver: ClientDriver = null

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

  def request(method: Method, path: String): ClientDriverRequest =
    RestClientDriver.onRequestTo(path).withMethod(method)

  def respondWith(status: Int, body: Option[HttpEntity] = None) = {
    body match {
      case Some(HttpEntity(b, c)) => RestClientDriver.giveResponse(b, c).withStatus(status)
      case _ => RestClientDriver.giveEmptyResponse().withStatus(status)
    }
  }

  def entity(content: String, contentType: String) = Some(HttpEntity(content, contentType))

  def onGetTo(path: String) = request(Method.GET, path)
  def onPutTo(path: String) = request(Method.PUT, path)
  def onPostTo(path: String) = request(Method.POST, path)
  def onDeleteTo(path: String) = request(Method.DELETE, path)
  def onRequestTo(method: String, path: String) =
    request(Method.custom(method), path)
}
