package org.anstreth.pcl

object ParserApp extends App {

  override def main(args: Array[String]): Unit = {
    println("Hello, Scala!")
  }

  def parse(s: String, parser: LexemsParser): List[String] = parser.parse(s)

}
