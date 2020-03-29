package controllers

import javax.inject._

import play.api.mvc._
import collection.mutable
import play.api.data._
import play.api.data.Forms._

case class LoginData(username: String, password: String)

@Singleton
class Application @Inject() (cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  val loginForm = Form(
    mapping(
      "Username" -> text(),
      "Password" -> text()
    )(LoginData.apply)(LoginData.unapply)
  )
  def login = Action{ implicit request =>
    Ok(views.html.login(loginForm))
  }
  def validateLogin = TODO
}
