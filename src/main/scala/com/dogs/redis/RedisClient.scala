package com.dogs.redis

import com.dogs.json._
import com.twitter.finagle.Redis._
import com.twitter.finagle.redis.Client
import com.twitter.finagle.redis.util.StringToChannelBuffer
import com.twitter.util.Future
import org.jboss.netty.buffer.ChannelBuffer

object RedisClient {
  def apply(): RedisClient = {
    new RedisClient(Client(newService("localhost:6379", "redis")))
  }
}

class RedisClient(client: Client) {
  private def set[T](key: ChannelBuffer, content: T, ttl: Long): Future[Unit] = {
    client.set(key, StringToChannelBuffer(toJson(content))) map { _ =>
      client.expire(key, ttl)
    }
  }


  def get[T](key: String, ttl: Long = 180)(onMiss: => Future[Option[T]])(implicit m: Manifest[T]): Future[Option[T]] = {
    val cacheKey = StringToChannelBuffer(key)
    client.get(cacheKey) flatMap {
      case Some(n) =>
        Future(Option(as[T](n.array)))
      case _ =>
        for {
          content <- onMiss
          set <- Future(content.fold()(set(cacheKey, _, ttl)))
        } yield content
    }
  }

  def flushAll() = client.flushAll()
}
