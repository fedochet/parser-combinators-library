package org.anstreth.pcl

import org.anstreth.pcl.Combinators.{alt, combine, rec}
import org.scalatest.{FlatSpec, Matchers}

class RecCombinatorTest extends FlatSpec with Matchers {
  private val aParser = SimpleTokenParser("a")
  private val recursive = rec(() => aParser)

  "A Recursive parser of SimpleTokenParser" should "behave like SimpleTokenParser" in {
    recursive.parse("a") should be ("", Success("a"))
    recursive.parse("ab") should be ("b", Success("a"))
    recursive.parse("b") should be ("b", Error())
  }

  it should "work with lambda pointing to itself" in {
    lazy val parser: Parser[String] = alt(
      SimpleTokenParser("b"),
      combine(SimpleTokenParser("a"), rec(()=>parser))(_+_)
    )

    parser.parse("aaaab") should be ("", Success("aaaab"))
    parser.parse("aaaa") should be ("aaaa", Error())
  }
}
