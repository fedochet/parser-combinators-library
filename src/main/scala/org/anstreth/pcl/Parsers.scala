package org.anstreth.pcl

import org.anstreth.pcl.Success.success

case class TokenParser[T](token: String, mapper: String => T) extends Parser[T] {
  override def parse(s: String) = s match {
    case x if x.startsWith(token) => (x.substring(token.length), success(mapper(token)))
    case _ => (s, Error())
  }
}

case class SimpleTokenParser(token: String) extends Parser[String] {
  private val tokenParser = TokenParser[String](token, identity)

  override def parse(s: String) = tokenParser.parse(s)
}