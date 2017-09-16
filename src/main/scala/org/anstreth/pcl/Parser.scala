package org.anstreth.pcl

trait Parser[T] {
  def parse(s: String): T
}

trait LexemsParser extends Parser[List[String]]