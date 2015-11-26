package http

object errors {
  sealed class HttpException(val status: Int, message: String = "") extends Exception(message)
  case class Unauthorized(message: String = "")        extends HttpException(401, message)
  case class NotFound(message: String = "")            extends HttpException(404, message)
}
