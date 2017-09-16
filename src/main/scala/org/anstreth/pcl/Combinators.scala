package org.anstreth.pcl

object Combinators {
  def combine[T1, T2, P](p1: Parser[T1], p2: Parser[T2])(combiner: (T1, T2) => P): Parser[P] = (original) => {
    val (remainder1, parseResult) = p1.parse(original)
    parseResult match {
      case Success(t1) => p2.parse(remainder1) match {
        case (remainder2, Success(t2)) => (remainder2, Success(combiner(t1, t2)))
        case (_, Error()) => (original, Error())
      }
      case Error() => (original, Error())
    }
  }

  def alt[T](p1: Parser[T], p2: Parser[T]): Parser[T] = (original) => {
    val (remainder1, parseResult) = p1.parse(original)
    parseResult match {
      case Success(_) => (remainder1, parseResult)
      case Error() => p2.parse(original)
    }
  }

  def opt[T](p: Parser[T], defaultValue: T): Parser[T] = (original) => {
    val parseResult = p.parse(original)
    parseResult match {
      case (_, Success(_)) => parseResult
      case (_, Error()) => (original, Success(defaultValue))
    }
  }
}