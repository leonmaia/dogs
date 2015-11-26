package http.hello

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import io.finch._


object endpoint {
  private def respond: Endpoint[String] = get("hello") {
    "hello world"
  }

  def makeService: Service[Request, Response] = respond.toService
}
