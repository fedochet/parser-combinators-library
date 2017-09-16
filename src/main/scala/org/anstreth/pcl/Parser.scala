package org.anstreth.pcl

trait Parser[T] {
  def parse(s: String): (String, ParseResult[T])
}