package org.anstreth.pcl.example

import org.anstreth.pcl.Combinators.{alt, combine, rec, repeat}
import org.anstreth.pcl.{ForgetToken, Parser, SimpleToken, Success}

/**
  * Simple equations grammar implementation:
  * E  -> E'+E | E'
  * E' -> num*E' | id | (E)*E' | (E)
  *
  * For brevity spaces are not supported
  */
object ExprParser extends App {

  override def main(args: Array[String]): Unit = {
    val digit = alt(
      SimpleToken("0"),
      SimpleToken("1"),
      SimpleToken("2"),
      SimpleToken("3"),
      SimpleToken("4"),
      SimpleToken("5"),
      SimpleToken("6"),
      SimpleToken("7"),
      SimpleToken("8"),
      SimpleToken("9")
    )

    val number = combine(digit, repeat(digit))
      .reduce(_ + _)
      .map(_.toInt)
      .map(Number)

    lazy val exprHatRec = rec(() => exprHat)
    lazy val exprRec = rec(() => expr)
    lazy val expr: Parser[Expr] =
      alt(
        combine(
          exprHatRec,
          ForgetToken("+"),
          exprRec
        ).reduce(Plus),
        exprHatRec
      )

    lazy val exprHat: Parser[Expr] =
      alt(
        combine(number, ForgetToken("*"), exprHatRec).reduce(Mul),
        number,
        combine(ForgetToken("("), exprRec, ForgetToken(")"), ForgetToken("*"), exprHatRec).reduce(Mul),
        combine(ForgetToken("("), exprRec, ForgetToken(")"))
      )

    val parsed = expr.parse(scala.io.StdIn.readLine())
    parsed match {
      case (_, Success(xs :: Nil)) =>
        println("Parsed tree is " + xs)
        println(xs.eval())
      case _ =>
        println("Something went wrong: " + parsed)
    }
  }
}
