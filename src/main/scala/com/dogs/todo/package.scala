package com.dogs

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import mesosphere.jackson.CaseClassModule

package object json {
  implicit val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.setSerializationInclusion(Include.NON_NULL)
  mapper.enable(SerializationFeature.INDENT_OUTPUT)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  mapper.registerModule(CaseClassModule)

  implicit def as[T](byteArray: Array[Byte])(implicit m: Manifest[T]): T =
    mapper.readValue[T](new String(byteArray))

  def toJson(value: Any): String =
    mapper.writeValueAsString(value)
}
