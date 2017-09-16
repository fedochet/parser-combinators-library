package org.anstreth.pcl

trait ParseResult[T]
case class Error() extends ParseResult[Any]
case class Success[T](t: T) extends ParseResult[T]
