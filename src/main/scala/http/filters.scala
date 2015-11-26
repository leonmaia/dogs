package http

import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.finagle.http._
import com.twitter.util.Future

object filters {

  /**
    * A simple Filter that checks that the request is valid by inspecting the
    * "Authorization" header.
    */
  class Authorize extends SimpleFilter[Request, Response] {
    def apply(request: Request, continue: Service[Request, Response]) = {
      if (request.headerMap.get(Fields.Authorization).contains("open sesame")) {
        continue(request)
      } else {
        Future.exception(new IllegalArgumentException("You don't know the secret"))
      }
    }
  }

  /**
    * A simple Filter that catches exceptions and converts them to appropriate
    * HTTP responses.
    */
  class HandleExceptions extends SimpleFilter[Request, Response] {
    def apply(request: Request, service: Service[Request, Response]) = {

      service(request) handle {
        case e: errors.NotFound => Response(Version.Http11, Status.NotFound)
        case e: IllegalArgumentException => Response(Version.Http11, Status.Forbidden)
        case _ => Response(Version.Http11, Status.InternalServerError)
      }
    }
  }
}
