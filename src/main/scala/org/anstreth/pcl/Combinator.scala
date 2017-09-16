package org.anstreth.pcl

trait Combinator[T] {
  def combine(p1: Parser[T], p2: Parser[T]): Parser[T]
}
