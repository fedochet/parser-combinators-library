package org.anstreth.pcl

trait ParseResult[+T]
case class Error() extends ParseResult[Nothing]
case class Success[T](t: T) extends ParseResult[T]
case object Success {
  def success[T](t: T): Success[T] = Success(t)
}
