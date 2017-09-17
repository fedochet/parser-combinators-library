package org.anstreth.pcl

trait Parser[+T] {
  def parse(s: String): (String, ParseResult[T])

  def reduce[T1>:T](combiner: (T1, T1) => T1): Parser[T1] = original => {
    parse(original) match {
      case (remainder, Success(t)) => (remainder, Success(List(t.reduce(combiner))))
      case (remainder, Error()) => (remainder, Error())
    }
  }

  def map[P](mapper: T => P): Parser[P] = original => {
    parse(original) match {
      case (remainder, Success(t)) => (remainder, Success(t.map(mapper)))
      case (remainder, Error()) => (remainder, Error())
    }
  }
}