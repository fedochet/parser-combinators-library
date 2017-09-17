package org.anstreth.pcl

object Combinators {
  def combine[T](p1: Parser[T], p2: Parser[T]): Parser[T] = original => {
    val (remainder1, parseResult) = p1.parse(original)
    parseResult match {
      case Success(t1) => p2.parse(remainder1) match {
        case (remainder2, Success(t2)) => (remainder2, Success(t1 ++ t2))
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

  def alt[T](p1: Parser[T], p2: Parser[T], pRest: Parser[T]*): Parser[T] = {
    pRest.foldLeft(alt(p1, p2))(alt)
  }


  def opt[T](p: Parser[T]): Parser[T] = (original) => {
    val parseResult = p.parse(original)
    parseResult match {
      case (_, Success(_)) => parseResult
      case (_, Error()) => (original, Success(Nil))
    }
  }

  def repeat[T](p: Parser[T]): Parser[T] = (original) => {
    val p1 = opt(combine(p, repeat(p)))
    p1.parse(original)
  }

  /**
    * Combinator to create recursive parsers poiniting to themselves.
    * To create local variable parsers use lazy keyword; if parser is a field variable,
    * lazy is not required.
    *
    * @param supplier a function which returns parser
    * @tparam T parameter type of parser
    * @return a parser which is equivalent to that returned from a supplier
    */
  def rec[T](supplier: () => Parser[T]): Parser[T] = (original) => supplier().parse(original)
}