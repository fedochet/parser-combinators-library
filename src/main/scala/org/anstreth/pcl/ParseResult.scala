package org.anstreth.pcl

trait ParseResult[+T]

case class Error() extends ParseResult[Nothing]

case class Success[T](t: List[T]) extends ParseResult[T]
