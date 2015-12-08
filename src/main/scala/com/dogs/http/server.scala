package com.dogs.http

import java.net.InetSocketAddress

import com.dogs.http.filters.{HandleExceptions, Authorize}
import com.dogs.todo.endpoint
import com.twitter.app.App
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http._
import com.twitter.finagle.zipkin.thrift.ZipkinTracer

object server extends App {

  val port: Int = 8080

  def main(): Unit = {
    val handleExceptions = new HandleExceptions
    val authorize = new Authorize

    // compose the Filters and Service together:
    val service = handleExceptions andThen authorize andThen endpoint.makeService

    ServerBuilder()
      .codec(Http())
      .bindTo(new InetSocketAddress(port))
      .name("hello")
      .tracer(ZipkinTracer.mk())
      .build(service)
  }
}