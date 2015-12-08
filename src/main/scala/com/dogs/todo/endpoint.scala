package com.dogs.todo

import com.dogs.json.mapper
import com.dogs.redis.RedisClient
import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Future
import io.finch._
import io.finch.jackson._

object endpoint {

  val client = RedisClient()

  private def respond: Endpoint[Option[Dog]] = get("dog" / string) { key: String =>
    val future = client.get[Dog](key)(Future(Option.empty))
    Ok(future)
  }

  def makeService: Service[Request, Response] = respond.toService
}
