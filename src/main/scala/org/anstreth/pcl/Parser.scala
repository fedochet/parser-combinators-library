package org.anstreth.pcl

import org.anstreth.pcl.Success.success

trait Parser[T] {
  def parse(s: String): (String, ParseResult[T])
  def map[P](map: T => P): Parser[P] = (original) => {
    parse(original) match {
      case (remainder, Success(t)) => (remainder, success(map(t)))
      case (remainder, Error()) => (remainder, Error());
    }
  }

}