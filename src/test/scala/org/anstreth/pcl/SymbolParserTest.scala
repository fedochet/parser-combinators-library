package org.anstreth.pcl

import org.scalatest.{FlatSpec, Matchers}

class SymbolParserTest extends FlatSpec with Matchers {
  "A SymbolParser" should "return success if passed string starts with its symbol" in {
    new SymbolParser('a').parse("abcd".toList) should be (("bcd".toList, Success('a')))
  }

  it should "return success if passed string contains only its symbol" in {
    new SymbolParser('a').parse(List('a')) should be ((Nil, Success('a')))
  }

  it should "return Error if passed string starts with other symbol" in {
    new SymbolParser('a').parse("bbcd".toList) should be (("bbcd".toList, Error()))
  }

  it should "return Error if passed string is empty" in {
    new SymbolParser('a').parse(Nil) should be (Nil, Error())
  }
}
