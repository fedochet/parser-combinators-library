package org.anstreth.pcl

trait Parser[T] {
  def parse(s: List[Char]): (List[Char], ParseResult[T])
}