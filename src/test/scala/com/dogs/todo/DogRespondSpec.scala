package com.dogs.todo

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, RequestBuilder, Response}
import io.finch.test.ServiceSuite
import org.scalatest.{Matchers, fixture}

case class Cat(name: String, kittens: Seq[Cat] = Seq.empty)

trait DogRespondSuite {
  this: fixture.FlatSpec with ServiceSuite with Matchers =>
  def createService(): Service[Request, Response] = {
    endpoint.makeService
  }

  it should "return HTTP 200 when hit /dog/" in { f =>
    val request: Request = RequestBuilder()
      .url("http://localhost:8080/dog/pug").buildGet()
    val result: Response = f(request)
    result.statusCode shouldBe 200
  }
}

class DogRespondSpec extends fixture.FlatSpec with ServiceSuite with DogRespondSuite with Matchers
