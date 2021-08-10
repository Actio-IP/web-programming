package hello

import javax.inject._

import play.api.Logging
import play.api.mvc._

class HelloController @Inject() (
  cc: ControllerComponents)
  extends AbstractController(cc) 
  with Logging {

  def index = Action { implicit request =>
    Ok(html.index())
  }

  def submit = Action { implicit request =>
    request.queryString.get("usersName").flatMap(_.headOption).filter(_.nonEmpty) match {
      case Some(name) =>
        logger.info(s"User supplied the name $name")
        Ok(html.result(name))
      case None =>
        logger.warn(s"User did not supply a name")
        BadRequest("Please supply a name")
    }
  }

}