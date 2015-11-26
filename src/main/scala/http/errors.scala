package http

object errors {
  sealed class HttpException(val status: Int, message: String = "") extends Exception(message)
  case class BadRequest(message: String = "")          extends HttpException(400, message)
  case class Unauthorized(message: String = "")        extends HttpException(401, message)
  case class PaymentRequired(message: String = "")     extends HttpException(402, message)
  case class Forbidden(message: String = "")           extends HttpException(403, message)
  case class NotFound(message: String = "")            extends HttpException(404, message)
  case class MethodNotAllowed(message: String = "")    extends HttpException(405, message)
  case class NotAcceptable(message: String = "")       extends HttpException(406, message)
  case class RequestTimeOut(message: String = "")      extends HttpException(408, message)
  case class Conflict(message: String = "")            extends HttpException(409, message)
  case class PreconditionFailed(message: String = "")  extends HttpException(412, message)
  case class TooManyRequests(message: String = "")     extends HttpException(429, message)
  case class InternalServerError(message: String = "") extends HttpException(500, message)
  case class NotImplemented(message: String = "")      extends HttpException(501, message)
  case class BadGateway(message: String = "")          extends HttpException(502, message)
  case class ServiceUnavailable(message: String = "")  extends HttpException(503, message)

}
