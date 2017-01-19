# rest-driver-scala
Scala syntactic sugar and [ScalaTest](http://scalatest.org) matchers for [rest-driver](https://github.com/rest-driver/rest-driver).

Note that you can use the core Java rest-driver library directly from Scala just fine - this
 library just provides a little syntactic sugar to streamline your tests.

## Usage
`rest-driver-scala` does not bundle rest-driver or scalatest so you will need to depend
on both `rest-driver-scala`, `rest-driver` and `scalatest`. In your sbt build include:

```scala
resolvers += Resolver.bintrayRepo("kelveden", "maven")

libraryDependencies ++= Seq(
  "com.kelveden" % "rest-driver-scala" % "1.0.1" % "test",
  "com.github.rest-driver" % "rest-client-driver" % "2.0.0" % "test",
  "com.github.rest-driver" % "rest-server-driver" % "2.0.0" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
```

Now in your scalatest spec:

```scala
import org.scalatest.{FunSpec, Matchers}
import com.github.restdriver.serverdriver.RestServerDriver.

class RestDrivenTest extends FunSpec with Matchers with RestDriven with RestDrivenMatchers {
  override val restDriverPort = <PORT THAT RESTDRIVER WILL LISTEN ON>
  val servicePort = <PORT YOUR SERVICE UNDER TEST LISTENS ON>
  val baseUrl = s"http://localhost:$servicePort"
      
  describe("my service") {
    it("calls my stubbed service") {
      expect(onGetTo("/my/stubbed/service"), respondWith(200))

      val response = RestServerDriver.get(s"$baseUrl/my/service/under/test")

      response should haveStatus(200)
    }
  }
}

```

For more examples of usage, see the [unit tests](./src/test/scala/com/kelveden/restdriverscala/RestDrivenTest.scala).

## Matchers
A simple set of scalatest matchers are available to match against a rest-driver `Response` object.
Just mix in the `RestDrivenMatchers` trait:

```scala
haveBodyThat(bodyMatcher: Matcher[String])
haveBodyContent(content: String)
haveStatus(status: Int)
haveHeader(name: String)
haveHeader(name: String, value: String)
```

See the [unit tests](./src/test/scala/com/kelveden/restdriverscala/RestDrivenMatchersTest.scala) for examples of usage.

## Logging
rest-driver provides some useful logging at `INFO` level via [SLF4J](https://www.slf4j.org/).
So, it's recommended that you provide some sort of SLF4J binding in your project for your tests.

You can see [an example](./src/test/resources/logback-test.xml) of using [Logback](http://logback.qos.ch/) to provide such a binding
in this very library.

## Using the Java API
As mentioned above, this library is simply syntactic sugar. So you can still leverage the core
Java API as little or as much as you like.

In particular, the `onXXXTo` (`onGetTo` et al) and `respondWith` functions return a Java
`ClientDriverRequest` and `ClientDriverResponse` object respectively that
can be manipulated in the usual way. So, for example:

```scala
expect(
  onGetTo("/my/stubbed/service").withHeader("myheader1", "myvalue1"),
  respondWith(200).withHeader("myheader2", "value2")
)
```
