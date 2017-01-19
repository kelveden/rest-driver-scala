package com.kelveden.restdriverscala

import com.github.restdriver.clientdriver.ClientDriverRequest.Method
import com.github.restdriver.clientdriver.{ClientDriverFactory, ClientDriverRequest, ClientDriverResponse, RestClientDriver, _}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}

trait RestDriven extends Suite with BeforeAndAfterAll with BeforeAndAfterEach {
  val restDriverPort: Int

  protected val factory = new ClientDriverFactory()
  protected var driver: ClientDriver = null
  type HttpHeaders = Map[String, String]
  type HttpEntity = (String, String)
  val EmptyEntity: HttpEntity = ("", "")

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

  private def request(method: Method, path: String, entity: HttpEntity, headers: HttpHeaders): ClientDriverRequest = {
    val builder = RestClientDriver.onRequestTo(path).withMethod(method)
    entity match {
      case EmptyEntity => builder
      case (b, c) => builder.withBody(b, c); builder
    }
  }

  def respondWith(status: Int, entity: HttpEntity = EmptyEntity, headers: Map[String, String] = Map.empty): ClientDriverResponse = {
    val response = entity match {
      case EmptyEntity => RestClientDriver.giveEmptyResponse().withStatus(status)
      case (b, c) => RestClientDriver.giveResponse(b, c).withStatus(status)
    }

    headers.foreach({
      case (name, value) => response.withHeader(name, value)
    })

    response
  }

  def entity(content: String, contentType: String) = Some((content, contentType))

  def onGetTo(path: String, entity: HttpEntity = EmptyEntity, headers: HttpHeaders = Map.empty) =
    request(Method.GET, path, entity, headers)
  def onPutTo(path: String, entity: HttpEntity = EmptyEntity, headers: HttpHeaders = Map.empty) =
    request(Method.PUT, path, entity, headers)
  def onPostTo(path: String, entity: HttpEntity = EmptyEntity, headers: HttpHeaders = Map.empty) =
    request(Method.POST, path, entity, headers)
  def onDeleteTo(path: String, entity: HttpEntity = EmptyEntity, headers: HttpHeaders = Map.empty) =
    request(Method.DELETE, path, entity, headers)
  def onRequestTo(method: String, path: String, entity: HttpEntity = EmptyEntity, headers: HttpHeaders = Map.empty) =
    request(Method.custom(method), path, entity, headers)
}
