package org.anstreth.pcl

case class TokenParser[T](token: String, mapper: String => T) extends Parser[T] {
  override def parse(s: String) = s match {
    case x if x.startsWith(token) => (x.substring(token.length), Success(List(mapper(token))))
    case _ => (s, Error())
  }
}

case class SimpleToken(token: String) extends Parser[String] {
  private val tokenParser = TokenParser[String](token, identity)

  override def parse(s: String) = {
    tokenParser.parse(s)
  }
}

case class ForgetToken(token: String) extends Parser[Nothing] {
  override def parse(s: String) = s match {
    case x if x.startsWith(token) => (x.substring(token.length), Success(Nil))
    case _ => (s, Error())
  }
}