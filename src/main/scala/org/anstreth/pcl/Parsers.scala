package org.anstreth.pcl

case class SymbolParser(symbol: Char) extends Parser[Char] {
  override def parse(s: List[Char]): (List[Char], ParseResult[Char]) = s match {
    case x::xs => if (x == symbol) (xs, Success(symbol)) else (s, Error())
    case Nil => (s, Error())
  }
}
