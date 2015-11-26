package http

import java.net.InetSocketAddress

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http._
import http.filters.{Authorize, HandleExceptions}
import http.hello.endpoint

object server {

  def main(args: Array[String]): Unit = {
    val handleExceptions = new HandleExceptions
    val authorize = new Authorize

    // compose the Filters and Service together:
    val service = handleExceptions andThen authorize andThen endpoint.makeService

    ServerBuilder()
      .codec(Http())
      .bindTo(new InetSocketAddress(8080))
      .name("hello")
      .build(service)
  }
}