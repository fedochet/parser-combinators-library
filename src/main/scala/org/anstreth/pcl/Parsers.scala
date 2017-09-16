package org.anstreth.pcl

case class TokenParser(token: String) extends Parser[String] {
  override def parse(s: String) = s match {
    case x if x.startsWith(token) => (x.substring(token.length), Success(token))
    case _ => (s, Error())
  }
}
